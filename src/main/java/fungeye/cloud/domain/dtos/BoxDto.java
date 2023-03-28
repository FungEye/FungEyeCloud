package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.Box} entity
 */
@Data
public class BoxDto implements Serializable {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}