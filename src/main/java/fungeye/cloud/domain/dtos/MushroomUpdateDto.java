package fungeye.cloud.domain.dtos;

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
    private List<IdealCondition> idealConditions;

    public MushroomUpdateDto(Long id, String name, String description, List<IdealCondition> idealConditions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idealConditions = idealConditions;
    }

    public MushroomUpdateDto() {}


}
