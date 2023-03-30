package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.MeasuredConditionIdDto;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static fungeye.cloud.service.mappers.DateTimeMapper.mapToDateDto;

public class MeasuredConditionsMapper {


    public static MeasuredConditionIdDto mapToIdDto(MeasuredConditionId conditionId)
    {
        MeasuredConditionIdDto dto = new MeasuredConditionIdDto();
        dto.setBoxId(conditionId.getBoxId());
        dto.setDateTime(mapToDateDto(conditionId.getDateTime()));

        return dto;
    }

    public static MeasuredConditionDto mapToDto(MeasuredCondition condtion )
    {
        MeasuredConditionDto dto = new MeasuredConditionDto();
        dto.setId(mapToIdDto(condtion.getId()));
        dto.setTemperature(condtion.getTemperature());
        dto.setHumidity(condtion.getHumidity());

        return dto;
    }

    public static List<MeasuredConditionDto> mapToDtoList(Set<MeasuredCondition> cond) {
        List<MeasuredConditionDto> list = new ArrayList<>();

        cond.forEach(c -> list.add(mapToDto(c)));

        return list;
    }

    public static List<MeasuredConditionDto> mapToDtoList(List<MeasuredCondition> cond) {
        List<MeasuredConditionDto> list = new ArrayList<>();

        cond.forEach(c -> list.add(mapToDto(c)));

        return list;
    }
}
