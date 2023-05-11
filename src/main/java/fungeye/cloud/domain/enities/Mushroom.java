package fungeye.cloud.domain.enities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "mushrooms")
public class Mushroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "origin")
    private String origin;

    @OneToMany(mappedBy = "mushroom")
    private Set<IdealCondition> idealConditions = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Set<IdealCondition> getIdealConditions() {
        return idealConditions;
    }

    public void setIdealConditions(Set<IdealCondition> idealConditions) {
        this.idealConditions = idealConditions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mushroom mushroom = (Mushroom) o;
        return Objects.equals(id, mushroom.id) && Objects.equals(name, mushroom.name) && Objects.equals(description, mushroom.description) && Objects.equals(origin, mushroom.origin) && Objects.equals(idealConditions, mushroom.idealConditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, origin, idealConditions);
    }

    @Override
    public String toString() {
        return "Mushroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", origin='" + origin + '\'' +
                ", idealConditions=" + idealConditions +
                '}';
    }
}