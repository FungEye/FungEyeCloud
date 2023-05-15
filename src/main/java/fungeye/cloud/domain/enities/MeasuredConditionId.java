package fungeye.cloud.domain.enities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/*
JPA struggles with composite keys, so we are using these classes to help with reading
from the database.
 */
@Data
@Embeddable
public class MeasuredConditionId implements Serializable {
    private static final long serialVersionUID = -1104957599303824325L;
    @NotNull
    @Column(name = "date_time", nullable = false)
    private Instant dateTime;

    @NotNull
    @Column(name = "box_id", nullable = false)
    private Long boxId;

}