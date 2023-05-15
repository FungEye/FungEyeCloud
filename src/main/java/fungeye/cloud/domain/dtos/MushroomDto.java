package fungeye.cloud.domain.dtos;

import lombok.*;

import java.util.Objects;

@Data
public class MushroomDto extends MushroomCreationDTO{

    private Long id;
    private String name;
    private String description;
    private String origin;
    private int userId;
}
