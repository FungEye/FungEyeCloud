package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.IdealConditionCreationDto;
import fungeye.cloud.domain.dtos.IdealConditionDto;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;

import java.util.ArrayList;
import java.util.List;

public class IdealConditionsMapper {
    private IdealConditionsMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static IdealConditionDto mapToIdealConditionDto (IdealCondition idealCondition) {
        IdealConditionDto dto = new IdealConditionDto();
        dto.setMushroomId(idealCondition.getId().getMushroomId());
        dto.setDevelopmentStage(idealCondition.getId().getDevelopmentStage());
        dto.setTempHigh(idealCondition.getTemperatureHigh());
        dto.setTempLow(idealCondition.getTemperatureLow());
        dto.setHumidityHigh(idealCondition.getHumidityHigh());
        dto.setHumidityLow(idealCondition.getHumidityLow());
        dto.setCo2High(idealCondition.getCo2High());
        dto.setCo2Low(idealCondition.getCo2Low());
        dto.setLightHigh(idealCondition.getLightHigh());
        dto.setLightLow(idealCondition.getLightLow());

        return dto;
    }

    public static IdealCondition mapToIdealCondition (IdealConditionDto dto)
    {
        IdealConditionId id = new IdealConditionId();
        id.setMushroomId(dto.getMushroomId());
        id.setDevelopmentStage(dto.getDevelopmentStage());

        IdealCondition idealCondition = new IdealCondition();
        idealCondition.setId(id);
        idealCondition.setTemperatureHigh(dto.getTempHigh());
        idealCondition.setTemperatureLow(dto.getTempLow());
        idealCondition.setHumidityHigh(dto.getHumidityHigh());
        idealCondition.setHumidityLow(dto.getHumidityLow());
        idealCondition.setCo2High(dto.getCo2High());
        idealCondition.setCo2Low(dto.getCo2Low());
        idealCondition.setLightHigh(dto.getLightHigh());
        idealCondition.setLightLow(dto.getLightLow());

        return idealCondition;

    }

    public static IdealCondition mapCreateToIdealCondition(IdealConditionCreationDto dto) {
        IdealConditionId id = new IdealConditionId();
        id.setDevelopmentStage(dto.getDevelopmentStage());

        IdealCondition idealCondition = new IdealCondition();
        idealCondition.setId(id);
        idealCondition.setTemperatureHigh(dto.getTempHigh());
        idealCondition.setTemperatureLow(dto.getTempLow());
        idealCondition.setHumidityHigh(dto.getHumidityHigh());
        idealCondition.setHumidityLow(dto.getHumidityLow());
        idealCondition.setCo2High(dto.getCo2High());
        idealCondition.setCo2Low(dto.getCo2Low());
        idealCondition.setLightHigh(dto.getLightHigh());
        idealCondition.setLightLow(dto.getLightLow());

        return idealCondition;
    }

    public static List<IdealConditionDto> mapToIdealConditionDtoList(List<IdealCondition> conditions) {
        List<IdealConditionDto> dtos = new ArrayList<>();
        conditions.forEach(condition -> dtos.add(mapToIdealConditionDto(condition)));

        return dtos;
    }

     public static List<IdealCondition> mapFromIdealConditionDtoList(List<IdealConditionDto> dtos) {
        List<IdealCondition> conditions = new ArrayList<>();
        dtos.forEach(dto -> conditions.add(mapToIdealCondition(dto)));

        return conditions;
     }
}
