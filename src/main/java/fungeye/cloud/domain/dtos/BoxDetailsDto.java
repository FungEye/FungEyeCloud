package fungeye.cloud.domain.dtos;

import lombok.*;

import java.util.List;

@Data
public class BoxDetailsDto {

    private Long id;
    private List<MeasuredConditionDto> conditions;
    private List<GrowDto> grows;
}
