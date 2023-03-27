package fungeye.cloud.domain.enities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/*
JPA struggles with composite keys, so we are using these classes to help with reading
from the database.
 */
@Getter
@Setter
@Embeddable
public class MeasuredConditionId implements Serializable {
    private static final long serialVersionUID = -1104957599303824325L;
    @NotNull
    @Column(name = "date_time", nullable = false)
    private Instant dateTime;

    @NotNull
    @Column(name = "box_id", nullable = false)
    private Long boxId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MeasuredConditionId entity = (MeasuredConditionId) o;
        return Objects.equals(this.dateTime, entity.dateTime) &&
                Objects.equals(this.boxId, entity.boxId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, boxId);
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }
}