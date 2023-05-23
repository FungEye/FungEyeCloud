package fungeye.cloud.domain.dtos.box;

import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.measured.MeasuredConditionDto;
import lombok.Data;

import java.util.List;

@Data
public class BoxDetailsDto {

    private Long id;
    private List<MeasuredConditionDto> conditions;
    private List<GrowDto> grows;
}
