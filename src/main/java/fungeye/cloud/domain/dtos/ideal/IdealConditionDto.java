package fungeye.cloud.domain.dtos.ideal;

import lombok.Data;

@Data
public class IdealConditionDto {
    private Long mushroomId;
    private String developmentStage;
    private double tempHigh;
    private double tempLow;
    private double humidityHigh;
    private double humidityLow;
    private double co2High;
    private double co2Low;
    private double lightHigh;
    private double lightLow;

    public void setDevelopmentStage(String developmentStage) {
        if (developmentStage.equals("spawn run") ||
                developmentStage.equals("pinning") ||
                developmentStage.equals("fruiting")) {
            this.developmentStage = developmentStage;
        }
        else {
            throw new IllegalArgumentException("Not a valid development stage");
        }
    }
}
