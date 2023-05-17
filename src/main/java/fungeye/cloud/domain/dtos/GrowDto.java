package fungeye.cloud.domain.dtos;


import lombok.Data;

@Data
public class GrowDto {

    private Long id;
    private DateTimeDto date;
    private String stage;
    private boolean isActive;
    private Long boxId;
    private Long mushroomId;

    public GrowDto(Long id, DateTimeDto date, String stage, boolean isActive, Long boxId) {
        this.id = id;
        this.date = date;
        this.stage = stage;
        this.isActive = isActive;
        this.boxId = boxId;
    }

    public GrowDto() {
    }
}
