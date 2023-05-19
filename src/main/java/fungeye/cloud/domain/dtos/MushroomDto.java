package fungeye.cloud.domain.dtos;

import lombok.Data;

@Data
public class MushroomDto {

    private Long id;
    private String name;
    private String description;
    private String origin;
    private String imageUrl;
}
