package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "boxes")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "box")
    private Set<Grow> grows = new LinkedHashSet<>();

    @OneToMany(mappedBy = "box")
    private Set<MeasuredCondition> measuredConditions = new LinkedHashSet<>();

}