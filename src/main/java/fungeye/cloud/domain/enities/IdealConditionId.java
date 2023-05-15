package fungeye.cloud.domain.enities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/*
JPA struggles with composite keys, so we are using these classes to help with reading
from the database.
 */
@Data
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

}