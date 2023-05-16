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

import java.lang.management.ManagementPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public MushroomDto updateMushroom(MushroomUpdateDto dto)
    {
        Mushroom toUpdate = mushroomRepository.findById(dto.getId()).orElseThrow();
        List<IdealCondition> found = idealConditionRepository.findByMushroom_Id(dto.getId());
        Set<IdealCondition> newConditions = dto.getIdealConditions();

        //List<IdealCondition> list = new ArrayList<>();

        if(toUpdate.getName().isEmpty())
        {
            throw new IllegalArgumentException("Please fill out all the necessary fields");
        }
        else {
            toUpdate.setName(dto.getName());
            toUpdate.setDescription(dto.getDescription());

            for (IdealCondition ideal : found) {


                newConditions.add(ideal);
                //IdealCondition cond = IdealConditionsMapper.mapUpdateToIdealConditions(ideal);

            }
            toUpdate.setIdealConditions(newConditions);

        }

        return MushroomMapper.mapToMushroomDto(toUpdate);
    }


}
