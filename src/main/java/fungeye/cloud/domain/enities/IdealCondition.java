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

}