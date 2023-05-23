package fungeye.cloud.domain.dtos.harvest;

import fungeye.cloud.domain.dtos.date.SimpleDateDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.Harvest} entity
 */
@Data
public class HarvestDetailsDto extends HarvestCreationDto implements Serializable {
    private Long id;
    @Size(max = 255)
    @NotNull
    private String mushroomName;
    private Long growId;
    private Double weight;
    private SimpleDateDto harvestDate;
    private String comment;
}