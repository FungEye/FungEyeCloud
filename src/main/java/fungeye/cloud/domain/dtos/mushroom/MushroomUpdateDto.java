package fungeye.cloud.domain.dtos.mushroom;

import fungeye.cloud.domain.dtos.ideal.IdealConditionDto;
import fungeye.cloud.domain.enities.IdealCondition;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
public class MushroomUpdateDto {
    private Long id;
    private String name;
    private String description;
    private String origin;
    private List<IdealConditionDto> idealConditions;
    private String imageUrl;
}
