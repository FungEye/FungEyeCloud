package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.dtos.GrowDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.persistence.repository.BoxRepository;
import org.springframework.stereotype.Service;

import static fungeye.cloud.service.mappers.BoxMapper.mapToBoxDto;
import static fungeye.cloud.service.mappers.BoxMapper.mapToSimpleDto;

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

    public BoxDetailsDto getById(Long id)
    {
        return mapToBoxDto(repository.findById(id).orElseThrow());
    }
}
