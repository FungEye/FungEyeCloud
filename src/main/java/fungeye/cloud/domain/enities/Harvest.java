package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mushroom getMushroom() {
        return mushroom;
    }

    public void setMushroom(Mushroom mushroom) {
        this.mushroom = mushroom;
    }

    public Grow getGrow() {
        return grow;
    }

    public void setGrow(Grow grow) {
        this.grow = grow;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDate getDateHarvested() {
        return dateHarvested;
    }

    public void setDateHarvested(LocalDate dateHarvested) {
        this.dateHarvested = dateHarvested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Harvest harvest = (Harvest) o;
        return Objects.equals(id, harvest.id) && Objects.equals(mushroom, harvest.mushroom) && Objects.equals(grow, harvest.grow) && Objects.equals(weight, harvest.weight) && Objects.equals(dateHarvested, harvest.dateHarvested);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mushroom, grow, weight, dateHarvested);
    }
}