package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "harvests")
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "mushroom_id", nullable = false)
    private Mushroom mushroom;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "grow_id")
    private Grow grow;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "date_harvested", nullable = false)
    private LocalDate dateHarvested;

}