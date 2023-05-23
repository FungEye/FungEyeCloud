package fungeye.cloud.domain.dtos.box;

import fungeye.cloud.domain.dtos.grow.GrowIdMushroomNameDto;
import lombok.Data;

import java.util.List;

@Data
public class SimpleBoxGrowDto {
    private Long id;
    private List<GrowIdMushroomNameDto> simpleGrowDtos;
}
