package fungeye.cloud.domain.dtos.box;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.Box} entity
 */
@Data
public class BoxDto implements Serializable {
    private Long id;

    public BoxDto(Long id) {
        this.id = id;
    }

    public BoxDto() {
    }
}