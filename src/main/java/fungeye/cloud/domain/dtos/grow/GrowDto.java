package fungeye.cloud.domain.dtos.grow;


import fungeye.cloud.domain.dtos.date.DateTimeDto;
import lombok.Data;

@Data
public class GrowDto {
    private Long id;
    private DateTimeDto date;
    private String stage;
    private boolean isActive;
    private Long boxId;
    private Long mushroomId;

    public GrowDto(Long id, DateTimeDto date, String stage, boolean isActive, Long boxId, Long mushroomId) {
        this.id = id;
        this.date = date;
        this.stage = stage;
        this.isActive = isActive;
        this.boxId = boxId;
        this.mushroomId = mushroomId;
    }

    public GrowDto() {
    }

    public void setStage(String stage) {
        if (stage.equals("spawn run") ||
                stage.equals("pinning") ||
                stage.equals("fruiting")) {
            this.stage = stage;
        } else {
            throw new IllegalArgumentException("Not a valid development stage");
        }
    }
}
