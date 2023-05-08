package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.IdealConditionDto;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;

public class IdealConditionsMapper {
    public static IdealConditionDto mapToIdealConditionDto (IdealCondition idealCondition) {
        IdealConditionDto dto = new IdealConditionDto();
        dto.setMushroomId(idealCondition.getId().getMushroomId());
        dto.setDevelopmentStage(idealCondition.getId().getDevelopmentStage());
        dto.setTempHigh(idealCondition.getTemperatureHigh());
        dto.setTempLow(dto.getTempLow());
        dto.setHumidityHigh(dto.getHumidityHigh());
        dto.setHumidityLow(dto.getHumidityLow());

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

        return idealCondition;

    }
}
