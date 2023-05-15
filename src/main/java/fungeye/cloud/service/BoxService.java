package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.service.mappers.BoxMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static fungeye.cloud.service.mappers.BoxMapper.*;

@Service
public class BoxService {

    private BoxRepository repository;

    public BoxService(BoxRepository repository) {
        this.repository = repository;
    }

    public BoxDto createBox()
    {
        return mapToSimpleDto(repository.save(new Box()));
    }

    public List<BoxDetailsDto> getAll() {
        return mapToBoxDtoList(repository.findAll());
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
            dtoList.add(BoxMapper.mapToSimpleDto(box));
        }
        return dtoList;
    }


}
