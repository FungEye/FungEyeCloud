package fungeye.cloud.domain.dtos;

import lombok.Data;

@Data
public class GrowUpdateDto {
    private Long id;
    private String developStage;
    private Boolean isActive;

}
