package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.dtos.MushroomUpdateDto;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.persistence.repository.MushroomRepository;
import fungeye.cloud.service.mappers.MushroomMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Mushroom> mushroom = repository.findById(id);
        if (mushroom.isPresent())
        {
            return MushroomMapper.mapToMushroomDto(mushroom.get());
        }
        else
        {
            throw new IllegalArgumentException("Mushroom not found");
        }
    }

    public List<MushroomDto> getAll()
    {
        List<Mushroom> allMushrooms = repository.findAll();
        List<MushroomDto> dtos = new ArrayList<>();
        for (Mushroom mushroom:
             allMushrooms) {
            dtos.add(MushroomMapper.mapToMushroomDto(mushroom));
        }
        return dtos;
    }

    public Mushroom updateMushroom(MushroomUpdateDto dto)
    {
        Optional<Mushroom> toUpdate = repository.findById(dto.getId());
        if(toUpdate.isPresent())
        {
            return MushroomMapper.mapUpdateMushroomDto(repository.save(dto));
        }
        else {
            throw  new IllegalArgumentException("Mushroom not found");
        }
    }
}
