package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.*;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.persistence.repository.UserRepository;
import fungeye.cloud.service.mappers.BoxMapper;
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

    public BoxService(BoxRepository repository, GrowRepository growRepository, UserRepository userRepository) {
        this.repository = repository;
        this.growRepository = growRepository;
        this.userRepository = userRepository;
    }

    public BoxDto createBox(BoxCreationDto dto) {
        Box toCreate = mapFromBoxCreationDto(dto);
        Optional<UserEntity> found = userRepository.findByUsername(dto.getUsername());
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
        return mapToBoxDto(repository.findById(id).orElseThrow());
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
