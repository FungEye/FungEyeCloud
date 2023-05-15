package fungeye.cloud.domain.dtos;


import lombok.*;

@Data
public class GrowDto {

    private Long id;
    private DateTimeDto date;
    private String stage;
    private boolean active;
    private Long boxId;

    public GrowDto(Long id, DateTimeDto date, String stage, boolean active, Long boxId) {
        this.id = id;
        this.date = date;
        this.stage = stage;
        this.active = active;
        this.boxId = boxId;
    }

    public GrowDto() {
    }
}
