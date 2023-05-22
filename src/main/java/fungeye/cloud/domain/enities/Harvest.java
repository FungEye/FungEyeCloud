package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

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

    @Size(max = 1000)
    @Column(name = "comment")
    private String comment;

}