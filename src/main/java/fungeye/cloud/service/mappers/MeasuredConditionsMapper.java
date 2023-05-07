package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.MeasuredConditionIdDto;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static fungeye.cloud.service.mappers.DateTimeMapper.mapToDateDto;
import static fungeye.cloud.service.mappers.DateTimeMapper.mapToInstant;

public class MeasuredConditionsMapper {


    public static MeasuredConditionIdDto mapToIdDto(MeasuredConditionId conditionId)
    {
        MeasuredConditionIdDto dto = new MeasuredConditionIdDto();
        dto.setBoxId(conditionId.getBoxId());
        dto.setDateTime(mapToDateDto(conditionId.getDateTime()));

        return dto;
    }

    public static MeasuredConditionDto mapToDto(MeasuredCondition condition)
    {
        MeasuredConditionDto dto = new MeasuredConditionDto();
        dto.setId(mapToIdDto(condition.getId()));
        dto.setTemperature(condition.getTemperature());
        dto.setHumidity(condition.getHumidity());

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

    public static MeasuredCondition mapToEntity(MeasuredConditionDto dto) {
        MeasuredCondition ent = new MeasuredCondition();

        MeasuredConditionId id = new MeasuredConditionId();
        id.setBoxId(dto.getId().getBoxId());
        id.setDateTime(mapToInstant(dto.getId().getDateTime()));

        ent.setId(id);
        ent.setHumidity(dto.getHumidity());
        ent.setTemperature(dto.getTemperature());

        return ent;
    }
}
