package fungeye.cloud.domain.dtos;


import lombok.*;

import java.io.Serializable;
import java.util.Objects;


@Data
public class HarvestCreationDto implements Serializable {
    private Long growId;
    private Double weight;
    private SimpleDateDto harvestDate;
}