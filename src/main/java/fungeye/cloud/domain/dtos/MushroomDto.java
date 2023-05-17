package fungeye.cloud.domain.dtos;

import lombok.Data;

@Data
public class MushroomDto extends MushroomCreationDTO{

    private Long id;
    private String name;
    private String description;
    private String origin;
}
