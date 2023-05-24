package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.ideal.IdealConditionCreationDto;
import fungeye.cloud.domain.dtos.ideal.IdealConditionDto;
import fungeye.cloud.domain.dtos.mushroom.*;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.IdealConditionRepository;
import fungeye.cloud.persistence.repository.MushroomRepository;
import fungeye.cloud.persistence.repository.UserRepository;
import fungeye.cloud.security.JwtGenerator;
import fungeye.cloud.service.mappers.IdealConditionsMapper;
import fungeye.cloud.service.mappers.MushroomMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j

@Service
public class MushroomService {
    private MushroomRepository repository;
    private UserRepository userRepository;
    private IdealConditionRepository idealConditionRepository;
    private JwtGenerator generator;


    public MushroomService(MushroomRepository repository, UserRepository userRepository,
                           IdealConditionRepository idealConditionRepository, JwtGenerator generator) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.idealConditionRepository = idealConditionRepository;
        this.generator = generator;
    }

    public MushroomDto createMushroom(MushroomCreationDTO toCreate) {
        Mushroom toSave = MushroomMapper.mapCreateToMushroom(toCreate);
        Optional<UserEntity> user = userRepository.findById(toCreate.getUserId());
        user.ifPresent(toSave::setUser);
        Mushroom saved = repository.save(toSave);
        return MushroomMapper.mapToMushroomDto(saved);
    }


    public MushroomWithConditionsDto createDefaultMushroom(DefaultMushroomCreationDto dto) {
        Mushroom toSave = MushroomMapper.mapDefaultCreateToMushroom(dto);
        Optional<UserEntity> user = userRepository.findById(3);
        return getMushroomWithConditionsDto(toSave, user, dto.getIdealConditionCreationDtos());
    }

    private MushroomWithConditionsDto getMushroomWithConditionsDto(Mushroom toSave, Optional<UserEntity> user,
                                                                   List<IdealConditionCreationDto> idealConditionCreationDtos) {
        user.ifPresent(toSave::setUser);
        Mushroom saved = repository.save(toSave);
        Long mushroomId = saved.getId();
        List<IdealConditionDto> savedConditionDtos = new ArrayList<>();
        if (idealConditionCreationDtos != null && !idealConditionCreationDtos.isEmpty()) {
            for (IdealConditionCreationDto idealDto :
                    idealConditionCreationDtos) {
                IdealCondition conditionToSave = IdealConditionsMapper.mapCreateToIdealCondition(idealDto);
                IdealConditionId id = conditionToSave.getId();
                id.setMushroomId(mushroomId);
                conditionToSave.setId(id);
                conditionToSave.setMushroom(saved);
                IdealConditionDto savedCondition = IdealConditionsMapper
                        .mapToIdealConditionDto(idealConditionRepository.save(conditionToSave));
                savedConditionDtos.add(savedCondition);
            }
        }
        MushroomWithConditionsDto mushroomWithConditionsDto = MushroomMapper.mapToMushroomWithConditionsDto(saved);
        mushroomWithConditionsDto.setIdealConditionDtos(savedConditionDtos);
        return mushroomWithConditionsDto;
    }

    public MushroomWithConditionsDto createCustomMushroom(CustomMushroomCreationDto dto) {
        Mushroom toSave = MushroomMapper.mapCustomCreateToMushroom(dto);
        Optional<UserEntity> user = userRepository.findByUsername(dto.getUsername());
        return getMushroomWithConditionsDto(toSave, user, dto.getIdealConditionCreationDtos());
    }

    public MushroomDto getByMushroomId(Long id) {
        Optional<Mushroom> mushroom = repository.findById(id);
        if (mushroom.isPresent() && !mushroom.get().getArchived()) {
            return MushroomMapper.mapToMushroomDto(mushroom.get());
        } else {
            throw new IllegalArgumentException("Mushroom not found");
        }
    }

    public List<MushroomDto> getAllDefault() {
        // 3 is the admin userId
        return getMushroomDtosById(3);
    }

    public List<MushroomDto> getCustom(String username) {
        return getMushroomDtosByUsername(username);
    }

    private List<MushroomDto> getMushroomDtosById(int userId) {
        List<Mushroom> mushrooms = repository.findByUser_Id(userId);
        List<MushroomDto> dtos = new ArrayList<>();
        for (Mushroom mushroom :
                mushrooms) {
            if (!mushroom.getArchived()) {
                dtos.add(MushroomMapper.mapToMushroomDto(mushroom));
            }
        }
        return dtos;
    }

    private List<MushroomDto> getMushroomDtosByUsername(String username) {
        List<Mushroom> mushrooms = repository.findByUser_Username(username);
        List<MushroomDto> dtos = new ArrayList<>();
        for (Mushroom mushroom :
                mushrooms) {
            if (!mushroom.getArchived()) {
                dtos.add(MushroomMapper.mapToMushroomDto(mushroom));
            }
        }
        return dtos;
    }

    public void archiveMushroom(long mushroomId, String token) {
        Mushroom entity = repository.findById(mushroomId).orElseThrow();
        UserEntity user = entity.getUser();
        String username = generator.getUsernameFromJwt(token.substring(7));
        if (user.getUsername().equals(username)) {
            if (entity.getArchived())
                throw new IllegalArgumentException(String.format("%s is already archived.", entity.getName()));
            repository.updateArchivedById(true, mushroomId);
        }
        else throw new BadCredentialsException(String.format("User: %s is not authorized to edit mushroom %s.", username, entity.getName()));

    }


    public MushroomDto updateMushroom(MushroomUpdateDto dto)
    {
        Mushroom toUpdate = repository.findById(dto.getId()).orElseThrow();
        List<IdealCondition> found = idealConditionRepository.findByMushroom_Id(dto.getId());
        Mushroom updated;

        if(toUpdate.getName().isEmpty())
        {
            throw new IllegalArgumentException("Please fill out all the necessary fields");
        }
        else {
            toUpdate.setName(dto.getName());
            toUpdate.setDescription(dto.getDescription());

            Set<IdealCondition> newConditions = new HashSet<>(found);
            toUpdate.setIdealConditions(newConditions);
            updated = repository.save(toUpdate);
            updated = toUpdate;
        }
        return MushroomMapper.mapToMushroomDto(updated);
    }

    private List<MushroomWithConditionsDto> getMushroomWithConditionDtosByUsername(String username) {
        List<Mushroom> mushrooms = repository.findByUser_Username(username);
        List<MushroomWithConditionsDto> dtos = new ArrayList<>();
        for (Mushroom mushroom :
                mushrooms) {
            MushroomWithConditionsDto dto = MushroomMapper.mapToMushroomWithConditionsDto(mushroom);
            List<IdealCondition> found = idealConditionRepository.findByMushroom_Id(dto.getId());
            List<IdealConditionDto> conditionDtos = IdealConditionsMapper.mapToIdealConditionDtoList(found);
            dto.setIdealConditionDtos(conditionDtos);

            int userId = mushroom.getUser().getId();
            Optional<UserEntity> user = userRepository.findById(userId);
            user.ifPresent(userEntity -> dto.setUsername(userEntity.getUsername()));

            if (!mushroom.getArchived()) {
                dtos.add(dto);
            }
        }

        return dtos;
    }

    public List<MushroomWithConditionsDto> getDefaultMushroomsWithConditions() {
        return getMushroomWithConditionDtosByUsername("admin");
    }

    public List<MushroomWithConditionsDto> getCustomMushroomsWithConditions(String username) {
        return getMushroomWithConditionDtosByUsername(username);
    }
}
