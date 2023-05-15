package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class HistoricalMeasurementDto {
    private List<SingleMeasurementDto> temperature;
    private List<SingleMeasurementDto> humidity;
    private List<SingleMeasurementDto> co2;
    private List<SingleMeasurementDto> light;
}
