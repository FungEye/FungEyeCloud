package fungeye.cloud.domain.dtos.mushroom;

import fungeye.cloud.domain.dtos.ideal.IdealConditionCreationDto;
import lombok.Data;

import java.util.List;

@Data
public class DefaultMushroomCreationDto {
    private String name;
    private String description;
    private String origin;
    private List<IdealConditionCreationDto> idealConditionCreationDtos;
    private String imageUrl;
}
