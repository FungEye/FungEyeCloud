package fungeye.cloud.domain.dtos.mushroom;

import fungeye.cloud.domain.dtos.ideal.IdealConditionDto;
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
