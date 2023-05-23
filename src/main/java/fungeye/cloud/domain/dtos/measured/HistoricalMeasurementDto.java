package fungeye.cloud.domain.dtos.measured;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HistoricalMeasurementDto implements Serializable {
    private List<SingleMeasurementDto> temperature;
    private List<SingleMeasurementDto> humidity;
    private List<SingleMeasurementDto> co2;
    private List<SingleMeasurementDto> light;
}
