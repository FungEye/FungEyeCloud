package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.util.List;

@Data
public class MushroomWithConditionsDto {
    private Long id;
    private String name;
    private String description;
    private String origin;
    private List<IdealConditionDto> idealConditionDtos;
    private String username;
    private String imageUrl;
}
