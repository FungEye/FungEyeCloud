package fungeye.cloud.domain.enities;

import fungeye.cloud.domain.enities.users.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "boxes")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "box", fetch = FetchType.LAZY)
    private Set<Grow> grows = new LinkedHashSet<>();

    @OneToMany(mappedBy = "box")
    private Set<MeasuredCondition> measuredConditions = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

}