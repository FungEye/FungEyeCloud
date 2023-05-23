package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.box.BoxCreationDto;
import fungeye.cloud.domain.dtos.box.BoxDetailsDto;
import fungeye.cloud.domain.dtos.box.BoxDto;
import fungeye.cloud.domain.dtos.box.SimpleBoxGrowDto;
import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.measured.MeasuredConditionDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import fungeye.cloud.persistence.repository.UserRepository;
import fungeye.cloud.service.mappers.BoxMapper;
import fungeye.cloud.service.mappers.MeasuredConditionsMapper;
import org.springframework.stereotype.Service;

import java.util.*;

import fungeye.cloud.persistence.repository.GrowRepository;
import fungeye.cloud.service.mappers.GrowMapper;

import static fungeye.cloud.service.mappers.BoxMapper.*;

@Service
public class BoxService {

    private BoxRepository repository;
    private GrowRepository growRepository;
    private UserRepository userRepository;
    private MeasuredConditionRepository measuredConditionRepository;

    public BoxService(BoxRepository repository, GrowRepository growRepository,
                      UserRepository userRepository, MeasuredConditionRepository measuredConditionRepository) {
        this.repository = repository;
        this.growRepository = growRepository;
        this.userRepository = userRepository;
        this.measuredConditionRepository = measuredConditionRepository;
    }

    public BoxDto createBox(BoxCreationDto dto) {
        Optional<UserEntity> found = userRepository.findByUsername(dto.getUsername());
        Box toCreate = mapFromBoxCreationDto(dto);
        found.ifPresent(toCreate::setUserEntity);
        Box saved = repository.save(toCreate);
        return mapToSimpleDto(saved);
    }

    public List<BoxDetailsDto> getAll() {
        List<BoxDetailsDto> boxDtoList = mapToBoxDtoList(repository.findAll());
        return setGrows(boxDtoList);
    }

    public BoxDetailsDto getById(Long id)
    {
        BoxDetailsDto boxDetailsDto = mapToBoxDto(repository.findById(id).orElseThrow());

        List<MeasuredCondition> measuredConditions = measuredConditionRepository.findAllByBox_Id(id);
        List<MeasuredConditionDto> measuredConditionDtos = MeasuredConditionsMapper.mapToDtoList(measuredConditions);
        boxDetailsDto.setConditions(measuredConditionDtos);

        List<Grow> grows = growRepository.findByBox_Id(id);
        Set<Grow> growSet = new HashSet<>(grows);
        List<GrowDto> growDtos = GrowMapper.mapToGrowDtoList(growSet);
        boxDetailsDto.setGrows(growDtos);

        return boxDetailsDto;
    }

    public List<BoxDto> getAllEmptyByUserName(String userName) {

        List<Box> boxes = repository.findBoxesByUserEntity_Username(userName);
        List<BoxDto> dtoList = new ArrayList<>();

        for (Box box : boxes)
        {
            if (box.getGrows().isEmpty())
            {
                dtoList.add(BoxMapper.mapToSimpleDto(box));
            }
            else{
                throw new IllegalArgumentException("There is no inactive grows!");
            }

        }
        return dtoList;
    }

    public List<SimpleBoxGrowDto> getAllByUsername(String username) {
        List<Box> boxes = repository.findByUserEntity_UsernameOrderByIdAsc(username);
        for (Box box:
             boxes) {
            List<Grow> grows = growRepository.findByBox_Id(box.getId());
            Set<Grow> growSet = new HashSet<>(grows);
            box.setGrows(growSet);
        }

        return mapToSimpleBoxGrowDtoList(boxes);
    }

    private List<BoxDetailsDto> setGrows(List<BoxDetailsDto> boxDtoList) {
        for (BoxDetailsDto boxDto:
                boxDtoList) {
            List<Grow> grows = growRepository.findByBox_Id(boxDto.getId());
            Set<Grow> growSet = new HashSet<>(grows);
            List<GrowDto> growDtos = GrowMapper.mapToGrowDtoList(growSet);
            boxDto.setGrows(growDtos);
        }

        return boxDtoList;
    }


}
