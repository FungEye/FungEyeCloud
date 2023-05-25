package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.ideal.IdealConditionCreationDto;
import fungeye.cloud.domain.dtos.ideal.IdealConditionDto;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;

import java.util.ArrayList;
import java.util.List;

public class IdealConditionsMapper {
    private IdealConditionsMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static IdealConditionDto mapToIdealConditionDto(IdealCondition idealCondition) {
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

    public static IdealCondition mapToIdealCondition(IdealConditionDto dto) {
        IdealConditionId id = new IdealConditionId();
        id.setMushroomId(dto.getMushroomId());

        return getIdealCondition(id, dto.getDevelopmentStage(), dto.getTempHigh(),
                dto.getTempLow(), dto.getHumidityHigh(), dto.getHumidityLow(),
                dto.getCo2High(), dto.getCo2Low(), dto.getLightHigh(), dto.getLightLow());
    }

    public static IdealCondition mapCreateToIdealCondition(IdealConditionCreationDto creationDto) {
        IdealConditionId id = new IdealConditionId();

        return getIdealCondition(id, creationDto.getDevelopmentStage(), creationDto.getTempHigh(),
                creationDto.getTempLow(), creationDto.getHumidityHigh(), creationDto.getHumidityLow(),
                creationDto.getCo2High(), creationDto.getCo2Low(), creationDto.getLightHigh(), creationDto.getLightLow());
    }

    private static IdealCondition getIdealCondition(IdealConditionId id, String developmentStage, double tempHigh,
                                                    double tempLow, double humidityHigh, double humidityLow,
                                                    double co2High, double co2Low, double lightHigh, double lightLow) {
        id.setDevelopmentStage(developmentStage);

        IdealCondition idealCondition = new IdealCondition();
        idealCondition.setId(id);
        idealCondition.setTemperatureHigh(tempHigh);
        idealCondition.setTemperatureLow(tempLow);
        idealCondition.setHumidityHigh(humidityHigh);
        idealCondition.setHumidityLow(humidityLow);
        idealCondition.setCo2High(co2High);
        idealCondition.setCo2Low(co2Low);
        idealCondition.setLightHigh(lightHigh);
        idealCondition.setLightLow(lightLow);

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
