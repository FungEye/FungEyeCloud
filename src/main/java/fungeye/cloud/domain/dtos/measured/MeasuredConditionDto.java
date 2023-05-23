package fungeye.cloud.domain.dtos.measured;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.MeasuredCondition} entity
 */
@Data
public class MeasuredConditionDto implements Serializable {
    private MeasuredConditionIdDto id;
    private Double co2;
    private Double light;
    private Double temperature;
    private Double humidity;
}