package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.util.List;

@Data
public class BoxDetailsDto {

    private Long id;
    private List<MeasuredConditionDto> conditions;
    private List<GrowDto> grows;

    public BoxDetailsDto(Long id, List<MeasuredConditionDto> conditions, List<GrowDto> grows) {
        this.id = id;
        this.conditions = conditions;
        this.grows = grows;
    }

    public BoxDetailsDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MeasuredConditionDto> getConditions() {
        return conditions;
    }

    public void setConditions(List<MeasuredConditionDto> conditions) {
        this.conditions = conditions;
    }

    public List<GrowDto> getGrows() {
        return grows;
    }

    public void setGrows(List<GrowDto> grows) {
        this.grows = grows;
    }
}
