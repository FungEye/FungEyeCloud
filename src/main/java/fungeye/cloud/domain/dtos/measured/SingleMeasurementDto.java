package fungeye.cloud.domain.dtos.measured;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SingleMeasurementDto implements Serializable {
    private Double value;
    private LocalDateTime dateTime;
}
