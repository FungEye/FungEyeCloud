package fungeye.cloud.domain.dtos.box;

import lombok.Data;
import java.io.Serializable;

@Data
public class BoxDto implements Serializable {
    private Long id;

    public BoxDto(Long id) {
        this.id = id;
    }

    public BoxDto() {
    }
}