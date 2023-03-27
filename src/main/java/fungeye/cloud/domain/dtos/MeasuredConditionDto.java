package fungeye.cloud.domain.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.MeasuredCondition} entity
 */
@Data
public class MeasuredConditionDto implements Serializable {
    private MeasuredConditionIdDto id;
    private BoxDto box;
    private Double temperature;
    private Double humidity;

    /**
     * A DTO for the {@link fungeye.cloud.domain.enities.MeasuredConditionId} entity
     */
    @Data
    public static class MeasuredConditionIdDto implements Serializable {
        @NotNull
        private Instant dateTime;
        @NotNull
        private Long boxId;
    }
}