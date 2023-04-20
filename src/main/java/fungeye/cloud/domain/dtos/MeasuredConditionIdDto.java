package fungeye.cloud.domain.dtos;

import java.io.Serializable;

public class MeasuredConditionIdDto implements Serializable {

    private Long boxId;
    private DateTimeDto dateTime;

    public MeasuredConditionIdDto(Long boxId, DateTimeDto dateTime) {
        this.boxId = boxId;
        this.dateTime = dateTime;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public void setDateTime(DateTimeDto dateTime) {
        this.dateTime = dateTime;
    }

    public MeasuredConditionIdDto() {
    }

    public Long getBoxId() {
        return boxId;
    }

    public DateTimeDto getDateTime() {
        return dateTime;
    }
}
