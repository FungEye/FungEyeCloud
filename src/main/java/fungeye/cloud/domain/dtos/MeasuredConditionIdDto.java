package fungeye.cloud.domain.dtos;

import lombok.*;

@Data
public class MeasuredConditionIdDto {

    private Long boxId;
    private DateTimeDto dateTime;

    public MeasuredConditionIdDto(Long boxId, DateTimeDto dateTime) {
        this.boxId = boxId;
        this.dateTime = dateTime;
    }

    public MeasuredConditionIdDto() {
    }
}
