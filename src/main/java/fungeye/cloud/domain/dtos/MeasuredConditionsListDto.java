package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import lombok.Data;

import java.util.List;

/**
 * A list DTO for the {@link fungeye.cloud.domain.enities.MeasuredCondition} entity
 */
@Data
public class MeasuredConditionsListDto {

    private List<MeasuredCondition> measuredConditionList;
    private MeasuredConditionRepository repo;

    public List<MeasuredCondition> getMeasuredConditionList() {
        return repo.findAll();
    }
}
