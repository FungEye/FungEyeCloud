package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "ideal_conditions")
public class IdealCondition {
    @EmbeddedId
    private IdealConditionId id;

    @MapsId("mushroomId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mushroom_id", nullable = false)
    private Mushroom mushroom;

    @NotNull
    @Column(name = "temperature_high", nullable = false)
    private Double temperatureHigh;

    @NotNull
    @Column(name = "temperature_low", nullable = false)
    private Double temperatureLow;

    @NotNull
    @Column(name = "humidity_high", nullable = false)
    private Double humidityHigh;

    @NotNull
    @Column(name = "humidity_low", nullable = false)
    private Double humidityLow;

    @Column(name = "co_2_low")
    private Double co2Low;

    @Column(name = "co_2_high")
    private Double co2High;

    @Column(name = "light_low")
    private Double lightLow;

    @Column(name = "light_high")
    private Double lightHigh;

    public IdealCondition() {
    }

    public IdealCondition(IdealConditionId id, Mushroom mushroom,
                          Double temperatureHigh, Double temperatureLow,
                          Double humidityHigh, Double humidityLow,
                          Double co2Low, Double co2High,
                          Double lightLow, Double lightHigh) {
        this.id = id;
        this.mushroom = mushroom;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.humidityHigh = humidityHigh;
        this.humidityLow = humidityLow;
        this.co2Low = co2Low;
        this.co2High = co2High;
        this.lightLow = lightLow;
        this.lightHigh = lightHigh;
    }

    public IdealConditionId getId() {
        return id;
    }

    public void setId(IdealConditionId id) {
        this.id = id;
    }

    public Mushroom getMushroom() {
        return mushroom;
    }

    public void setMushroom(Mushroom mushroom) {
        this.mushroom = mushroom;
    }

    public Double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(Double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public Double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(Double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public Double getHumidityHigh() {
        return humidityHigh;
    }

    public void setHumidityHigh(Double humidityHigh) {
        this.humidityHigh = humidityHigh;
    }

    public Double getHumidityLow() {
        return humidityLow;
    }

    public void setHumidityLow(Double humidityLow) {
        this.humidityLow = humidityLow;
    }

    public Double getCo2Low() {
        return co2Low;
    }

    public void setCo2Low(Double co2Low) {
        this.co2Low = co2Low;
    }

    public Double getCo2High() {
        return co2High;
    }

    public void setCo2High(Double co2High) {
        this.co2High = co2High;
    }

    public Double getLightLow() {
        return lightLow;
    }

    public void setLightLow(Double lightLow) {
        this.lightLow = lightLow;
    }

    public Double getLightHigh() {
        return lightHigh;
    }

    public void setLightHigh(Double lightHigh) {
        this.lightHigh = lightHigh;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdealCondition that = (IdealCondition) o;
        return Objects.equals(id, that.id) && Objects.equals(mushroom, that.mushroom) && Objects.equals(temperatureHigh, that.temperatureHigh) && Objects.equals(temperatureLow, that.temperatureLow) && Objects.equals(humidityHigh, that.humidityHigh) && Objects.equals(humidityLow, that.humidityLow) && Objects.equals(co2Low, that.co2Low) && Objects.equals(co2High, that.co2High) && Objects.equals(lightLow, that.lightLow) && Objects.equals(lightHigh, that.lightHigh);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mushroom, temperatureHigh, temperatureLow, humidityHigh, humidityLow, co2Low, co2High, lightLow, lightHigh);
    }

    @Override
    public String toString() {
        return "IdealCondition{" +
                "id=" + id +
                ", mushroom=" + mushroom +
                ", temperatureHigh=" + temperatureHigh +
                ", temperatureLow=" + temperatureLow +
                ", humidityHigh=" + humidityHigh +
                ", humidityLow=" + humidityLow +
                ", co2Low=" + co2Low +
                ", co2High=" + co2High +
                ", lightLow=" + lightLow +
                ", lightHigh=" + lightHigh +
                '}';
    }
}