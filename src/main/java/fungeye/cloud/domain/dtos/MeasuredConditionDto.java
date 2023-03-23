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
    private final MeasuredConditionIdDto id;
    private final BoxDto box;
    private final Double temperature;
    private final Double humidity;

    /**
     * A DTO for the {@link fungeye.cloud.domain.enities.MeasuredConditionId} entity
     */
    @Data
    public static class MeasuredConditionIdDto implements Serializable {
        @NotNull
        private final Instant dateTime;
        @NotNull
        private final Long boxId;
    }
}