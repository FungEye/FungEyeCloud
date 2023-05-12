package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.Harvest} entity
 */
@Data
public class HarvestCreationDto implements Serializable {
    private final Long growId;
    private final Double weight;
    private final SimpleDateDto harvestDate;
}