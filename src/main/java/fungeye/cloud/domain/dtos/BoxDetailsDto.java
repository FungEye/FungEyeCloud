package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.enities.Grow;
import lombok.Data;

import java.util.List;

@Data
public class BoxDetailsDto {

    private Long id;
    private List<MeasuredConditionDto> measuredConditionDtoList;
    private List<Grow> grows;

    public BoxDetailsDto(Long id, List<MeasuredConditionDto> measuredConditionDtoList, List<Grow> grows) {
        this.id = id;
        this.measuredConditionDtoList = measuredConditionDtoList;
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

    public List<MeasuredConditionDto> getMeasuredConditionDtoList() {
        return measuredConditionDtoList;
    }

    public void setMeasuredConditionDtoList(List<MeasuredConditionDto> measuredConditionDtoList) {
        this.measuredConditionDtoList = measuredConditionDtoList;
    }

    public List<Grow> getGrows() {
        return grows;
    }

    public void setGrows(List<Grow> grows) {
        this.grows = grows;
    }
}
