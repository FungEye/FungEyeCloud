package fungeye.cloud.domain.dtos.measured;

import fungeye.cloud.domain.dtos.date.DateTimeDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class MeasuredConditionIdDto implements Serializable {

    private Long boxId;
    private DateTimeDto dateTime;

    public MeasuredConditionIdDto(Long boxId, DateTimeDto dateTime) {
        this.boxId = boxId;
        this.dateTime = dateTime;
    }

    public MeasuredConditionIdDto() {
    }
}
