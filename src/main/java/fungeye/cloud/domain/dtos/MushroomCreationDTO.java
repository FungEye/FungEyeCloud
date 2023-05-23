package fungeye.cloud.domain.dtos;


import lombok.Data;

@Data
public class MushroomCreationDTO {
    private String name;
    private String description;
    private String origin;
    private int userId;
    private String imageUrl;
}
