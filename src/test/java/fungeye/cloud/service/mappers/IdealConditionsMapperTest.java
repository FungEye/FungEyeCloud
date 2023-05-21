package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.IdealConditionCreationDto;
import fungeye.cloud.domain.dtos.IdealConditionDto;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;
import fungeye.cloud.domain.enities.Mushroom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdealConditionsMapperTest {
    private IdealCondition idealCondition;
    private IdealConditionDto dto;
    private IdealConditionCreationDto creationDto;

    @BeforeEach
    void setUp() {
        idealCondition = new IdealCondition();
        IdealConditionId id = new IdealConditionId();
        id.setMushroomId(1L);
        id.setDevelopmentStage("fruiting");
        idealCondition.setId(id);
        idealCondition.setTemperatureHigh(27.0);
        idealCondition.setTemperatureLow(24.0);
        idealCondition.setHumidityHigh(80.0);
        idealCondition.setHumidityLow(70.0);
        idealCondition.setCo2High(800.0);
        idealCondition.setCo2Low(200.0);
        idealCondition.setLightHigh(1000.0);
        idealCondition.setLightLow(200.0);

        dto = new IdealConditionDto();
        dto.setMushroomId(1L);
        dto.setDevelopmentStage("fruiting");
        dto.setTempHigh(27.0);
        dto.setTempLow(24.0);
        dto.setHumidityHigh(80.0);
        dto.setHumidityLow(70.0);
        dto.setCo2High(800.0);
        dto.setCo2Low(200.0);
        dto.setLightHigh(1000.0);
        dto.setLightLow(200.0);

        creationDto = new IdealConditionCreationDto();
        creationDto.setDevelopmentStage("fruiting");
        creationDto.setTempHigh(27.0);
        creationDto.setTempLow(24.0);
        creationDto.setHumidityHigh(80.0);
        creationDto.setHumidityLow(70.0);
        creationDto.setCo2High(800.0);
        creationDto.setCo2Low(200.0);
        creationDto.setLightHigh(1000.0);
        creationDto.setLightLow(200.0);
    }

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<IdealConditionsMapper> constructor = IdealConditionsMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testMapToIdealConditionDto() {
        IdealConditionDto mappedDto = IdealConditionsMapper.mapToIdealConditionDto(idealCondition);
        Assertions.assertEquals(dto, mappedDto);
    }

    @Test
    void testMapToIdealCondition() {
        IdealCondition mappedIdealCondition = IdealConditionsMapper.mapToIdealCondition(dto);
        Assertions.assertEquals(idealCondition, mappedIdealCondition);
    }

    @Test
    void testMapCreateToIdealCondition() {
        IdealCondition mappedIdealCondition = IdealConditionsMapper.mapCreateToIdealCondition(creationDto);
        IdealConditionId mappedId = mappedIdealCondition.getId();
        mappedId.setMushroomId(1L);
        mappedIdealCondition.setId(mappedId);
        Assertions.assertEquals(idealCondition, mappedIdealCondition);
    }

    @Test
    void testMapToIdealConditionList() {
        List<IdealConditionDto> dtoList = new ArrayList<>();
        dtoList.add(dto);

        List<IdealCondition> conditionList = new ArrayList<>();
        conditionList.add(idealCondition);

        List<IdealCondition> mappedConditions = IdealConditionsMapper.mapFromIdealConditionDtoList(dtoList);

        assertEquals(conditionList, mappedConditions);
    }
}