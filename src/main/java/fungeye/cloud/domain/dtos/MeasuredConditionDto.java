package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.MeasuredCondition} entity
 */
@Data
public class MeasuredConditionDto implements Serializable {
    private MeasuredConditionIdDto id;
    private Double co2;
    private Double light;
    private Double temperature;
    private Double humidity;

    public void setId(MeasuredConditionIdDto id) {
        this.id = id;
    }

    public MeasuredConditionIdDto getId() {
        return id;
    }

    public Double getCo2() { return co2; }

    public void setCo2(Double co2) { this.co2 = co2; }

    public void setLight(Double light) { this.light = light; }

    public Double getLight() { return light; }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
}