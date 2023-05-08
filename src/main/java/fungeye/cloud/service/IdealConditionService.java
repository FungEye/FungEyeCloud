package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.IdealConditionDto;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.persistence.repository.IdealConditionRepository;
import fungeye.cloud.service.mappers.IdealConditionsMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<IdealConditionDto> getByMushroomId(Long mushroomId)
    {
        List<IdealCondition> found = repository.findByMushroom_Id(mushroomId);

        List<IdealConditionDto> outDtos = new ArrayList<>();
        if (found != null)
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
}
