package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CustomMushroomCreationDto {
    private String name;
    private String description;
    private String origin;
    private List<IdealConditionCreationDto> idealConditionCreationDtos;
    private String username;
}
