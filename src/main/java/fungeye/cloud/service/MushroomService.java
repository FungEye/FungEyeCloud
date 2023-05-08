package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.persistence.repository.MushroomRepository;
import fungeye.cloud.service.mappers.MushroomMapper;
import org.springframework.stereotype.Service;

@Service
public class MushroomService {
    private MushroomRepository repository;

    public MushroomService(MushroomRepository repository) {
        this.repository = repository;
    }

    public MushroomDto createMushroom(MushroomCreationDTO toCreate)
    {
        Mushroom toSave = MushroomMapper.mapCreateToMushroom(toCreate);
        return MushroomMapper.mapToMushroomDto(repository.save(toSave));
    }

    public MushroomDto getById(Long id)
    {
        if (repository.existsById(id)) {
            return MushroomMapper.mapToMushroomDto(repository.findById(id).get());
        }
        else
        {
            throw new IllegalArgumentException("Mushroom not found");
        }
    }
}
