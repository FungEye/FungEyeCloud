package fungeye.cloud.domain.enities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

/*
JPA struggles with composite keys, so we are using these classes to help with reading
from the database.
 */
@Getter
@Setter
@Embeddable
public class IdealConditionId implements Serializable {
    private static final long serialVersionUID = 8773972825802563132L;
    @NotNull
    @Column(name = "mushroom_id", nullable = false)
    private Long mushroomId;

    @Size(max = 255)
    @NotNull
    @Column(name = "development_stage", nullable = false)
    private String developmentStage;

    public IdealConditionId() {

    }

    public IdealConditionId(Long mushroomId, String developmentStage) {
        this.mushroomId = mushroomId;
        this.developmentStage = developmentStage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IdealConditionId entity = (IdealConditionId) o;
        return Objects.equals(this.mushroomId, entity.mushroomId) &&
                Objects.equals(this.developmentStage, entity.developmentStage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mushroomId, developmentStage);
    }

    public Long getMushroomId() {
        return mushroomId;
    }

    public void setMushroomId(Long mushroomId) {
        this.mushroomId = mushroomId;
    }

    public String getDevelopmentStage() {
        return developmentStage;
    }

    public void setDevelopmentStage(String developmentStage) {
        this.developmentStage = developmentStage;
    }

    @Override
    public String toString() {
        return "IdealConditionId{" +
                "mushroomId=" + mushroomId +
                ", developmentStage='" + developmentStage + '\'' +
                '}';
    }
}