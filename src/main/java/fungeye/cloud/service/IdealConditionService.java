package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.IdealConditionDto;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.persistence.repository.IdealConditionRepository;
import fungeye.cloud.service.mappers.IdealConditionsMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IdealConditionService {
    private IdealConditionRepository repository;

    public IdealConditionService(IdealConditionRepository repository) {
        this.repository = repository;
    }

    public IdealConditionDto createIdealCondition(IdealConditionDto dtoIn)
    {
        IdealCondition conditionToSave = IdealConditionsMapper.mapToIdealCondition(dtoIn);
        return IdealConditionsMapper.mapToIdealConditionDto(repository.save(conditionToSave));
    }

    //todo maybe this method should be called getIdealConditionsByMushroomId()?
    public List<IdealConditionDto> getByMushroomId(Long mushroomId)
    {
        List<IdealCondition> found = repository.findByMushroom_Id(mushroomId);

        List<IdealConditionDto> outDtos = new ArrayList<>();
        if (found != null && !found.isEmpty())
        {
            for (IdealCondition condition:
                 found) {
                outDtos.add(IdealConditionsMapper.mapToIdealConditionDto(condition));
            }
            return outDtos;
        }
        else
        {
            throw new IllegalArgumentException("No ideal conditions were found for that mushroom");
        }
    }

    public List<IdealConditionDto> updateIdealConditionsByMushroomId(IdealConditionDto dto)
    {
        List<IdealCondition> ideal = repository.findByMushroom_Id(dto.getMushroomId());

        if (ideal != null &&
                !ideal.isEmpty())
        {
            for (IdealCondition condition : ideal)
            {
                dto.setLightLow(dto.getLightLow());
                dto.setLightHigh(dto.getLightHigh());
                dto.setCo2Low(dto.getCo2Low());
                dto.setCo2High(dto.getCo2High());
                dto.setTempLow(dto.getTempLow());
                dto.setTempHigh(dto.getTempHigh());
                dto.setHumidityLow(dto.getHumidityLow());
                dto.setLightHigh(dto.getLightHigh());

                repository.save(IdealConditionsMapper.mapToIdealCondition(dto));
            }
        }

        return
    }
}
