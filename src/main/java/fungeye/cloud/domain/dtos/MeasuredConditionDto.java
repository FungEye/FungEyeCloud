package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.MeasuredCondition} entity
 */
@Data
public class MeasuredConditionDto implements Serializable {
    private MeasuredConditionIdDto id;
    private Double temperature;
    private Double humidity;

    public void setId(MeasuredConditionIdDto id) {
        this.id = id;
    }

    public MeasuredConditionIdDto getId() {
        return id;
    }


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