package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Data
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
                          Double co2High, Double co2Low,
                          Double lightHigh, Double lightLow) {
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


}