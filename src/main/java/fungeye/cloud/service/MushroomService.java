package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.dtos.MushroomUpdateDto;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.persistence.repository.IdealConditionRepository;
import fungeye.cloud.persistence.repository.MushroomRepository;
import fungeye.cloud.service.mappers.IdealConditionsMapper;
import fungeye.cloud.service.mappers.MushroomMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MushroomService {
    private MushroomRepository mushroomRepository;
    private IdealConditionRepository idealConditionRepository;

    public MushroomService(MushroomRepository mushroomRepository, IdealConditionRepository idealConditionRepository) {
        this.mushroomRepository = mushroomRepository;
        this.idealConditionRepository = idealConditionRepository;
    }

    public MushroomDto createMushroom(MushroomCreationDTO toCreate)
    {
        Mushroom toSave = MushroomMapper.mapCreateToMushroom(toCreate);
        return MushroomMapper.mapToMushroomDto(mushroomRepository.save(toSave));
    }

    public MushroomDto getById(Long id)
    {
        Optional<Mushroom> mushroom = mushroomRepository.findById(id);
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
        List<Mushroom> allMushrooms = mushroomRepository.findAll();
        List<MushroomDto> dtos = new ArrayList<>();
        for (Mushroom mushroom:
             allMushrooms) {
            dtos.add(MushroomMapper.mapToMushroomDto(mushroom));
        }
        return dtos;
    }

    public MushroomUpdateDto updateMushroom(MushroomUpdateDto dto)
    {
        Optional<Mushroom> toUpdate = mushroomRepository.findById(dto.getId());
        if(toUpdate.isPresent())
        {
            List<IdealCondition> found = idealConditionRepository.findByMushroom_Id(dto.getId());


            return MushroomMapper.mapUpdateMushroomDto(mushroomRepository.save(dto));
        }
        else {
            throw  new IllegalArgumentException("Mushroom not found");
        }
    }
}
