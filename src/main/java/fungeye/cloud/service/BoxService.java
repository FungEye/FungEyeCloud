package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.dtos.GrowDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.service.mappers.BoxMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import fungeye.cloud.persistence.repository.GrowRepository;
import fungeye.cloud.service.mappers.GrowMapper;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fungeye.cloud.service.mappers.BoxMapper.*;

@Service
public class BoxService {

    private BoxRepository repository;
    private GrowRepository growRepository;

    public BoxService(BoxRepository repository, GrowRepository growRepository) {
        this.repository = repository;
        this.growRepository = growRepository;
    }

    public BoxDto createBox()
    {
        return mapToSimpleDto(repository.save(new Box()));
    }

    public List<BoxDetailsDto> getAll() {
        List<BoxDetailsDto> boxDtoList = mapToBoxDtoList(repository.findAll());
        for (BoxDetailsDto boxDto:
             boxDtoList) {
            List<Grow> grows = growRepository.findByBox_Id(boxDto.getId());
            Set<Grow> growSet = new HashSet<>(grows);
            List<GrowDto> growDtos = GrowMapper.mapToGrowDtoList(growSet);
            boxDto.setGrows(growDtos);
        }

        return boxDtoList;
    }

    public BoxDetailsDto getById(Long id)
    {
        return mapToBoxDto(repository.findById(id).orElseThrow());
    }

    public List<BoxDto> getAllByUserName(String userName) {

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


}
