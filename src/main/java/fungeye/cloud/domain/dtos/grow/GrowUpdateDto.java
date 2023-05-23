package fungeye.cloud.domain.dtos.grow;

import lombok.Data;

@Data
public class GrowUpdateDto {
    private Long id;
    private String developStage;
    private Boolean isActive;

    public void setDevelopmentStage(String developStage) {
        if (developStage.equals("spawn run") ||
                developStage.equals("pinning") ||
                developStage.equals("fruiting")) {
            this.developStage = developStage;
        }
        else {
            throw new IllegalArgumentException("Not a valid development stage");
        }
    }
}
