package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class SingleMeasurementDto {
    private Double value;
    private LocalDateTime dateTime;

    /*
     response: {
     temp: {
     values: [ {value: 7, datetime: 20/05},...]
     },
     co2: ..., }
     */
}
