package fungeye.cloud.domain.dtos.grow;

import fungeye.cloud.domain.dtos.date.SimpleDateDto;
import lombok.Data;

@Data
public class GrowCreationDto {

    private Long boxId;
    private Long mushroomId;
    private String username;
    private SimpleDateDto date;
    private String developStage;

    public void setDevelopStage(String developStage) {
        if (developStage.equals("spawn run") ||
                developStage.equals("pinning") ||
                developStage.equals("fruiting")) {
            this.developStage = developStage;
        } else {
            throw new IllegalArgumentException("Not a valid development stage");
        }
    }


}
