package fungeye.cloud.domain.dtos;

import lombok.Data;

@Data
public class IdealConditionCreationDto {
    private String developmentStage;
    private double tempHigh;
    private double tempLow;
    private double humidityHigh;
    private double humidityLow;
    private double co2High;
    private double co2Low;
    private double lightHigh;
    private double lightLow;
}
