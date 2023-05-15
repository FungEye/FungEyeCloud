package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "measured_conditions")
public class MeasuredCondition {
    @EmbeddedId
    private MeasuredConditionId id;

    @MapsId("boxId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "box_id", nullable = false)
    private Box box;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "humidity")
    private Double humidity;

    @Column(name = "co2")
    private Double co2;

    @Column(name = "light")
    private Double light;

}