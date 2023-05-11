package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.enities.IdealCondition;

import java.util.Objects;
import java.util.Set;

public class MushroomUpdateDto {
    private Long id;
    private String name;
    private String description;
    private Set<IdealCondition> idealConditions;

    public MushroomUpdateDto(Long id, String name, String description, Set<IdealCondition> idealConditions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.idealConditions = idealConditions;
    }

    public MushroomUpdateDto() {}

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

    public Set<IdealCondition> getIdealConditions() {
        return idealConditions;
    }

    public void setIdealConditions(Set<IdealCondition> idealConditions) {
        this.idealConditions = idealConditions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "MushroomDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
