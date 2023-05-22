package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SimpleBoxGrowDto {
    private Long id;
    private List<GrowIdMushroomNameDto> simpleGrowDtos;
}
