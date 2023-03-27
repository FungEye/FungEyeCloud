package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
}