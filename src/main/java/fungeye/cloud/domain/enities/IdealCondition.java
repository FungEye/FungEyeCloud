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

    @NotNull
    @Column(name = "")

    public IdealCondition() {
    }

    public IdealCondition(IdealConditionId id, Mushroom mushroom, Double temperatureHigh, Double temperatureLow, Double humidityHigh, Double humidityLow) {
        this.id = id;
        this.mushroom = mushroom;
        this.temperatureHigh = temperatureHigh;
        this.temperatureLow = temperatureLow;
        this.humidityHigh = humidityHigh;
        this.humidityLow = humidityLow;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdealCondition that = (IdealCondition) o;
        return Objects.equals(id, that.id) && Objects.equals(mushroom, that.mushroom) && Objects.equals(temperatureHigh, that.temperatureHigh) && Objects.equals(temperatureLow, that.temperatureLow) && Objects.equals(humidityHigh, that.humidityHigh) && Objects.equals(humidityLow, that.humidityLow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mushroom, temperatureHigh, temperatureLow, humidityHigh, humidityLow);
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
                '}';
    }
}