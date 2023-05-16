package fungeye.cloud.domain.dtos;

import lombok.Data;

@Data
public class GrowCreationDto {

    private Long boxId;
    private Long mushroomId;
    private String username;
    private SimpleDateDto date;
    private String developStage;


}
