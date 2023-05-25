package fungeye.cloud.domain.dtos.harvest;

import fungeye.cloud.domain.dtos.date.SimpleDateDto;
import lombok.Data;
import java.io.Serializable;

@Data
public class HarvestCreationDto implements Serializable {
    private Long growId;
    private Double weight;
    private SimpleDateDto harvestDate;
    private String comment;
}