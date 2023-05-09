package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.IdealConditionDto;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IdealConditionsMapperTest {
    private IdealCondition idealCondition;
    private IdealConditionDto dto;

    @BeforeEach
    void setUp() {
        idealCondition = new IdealCondition();
        IdealConditionId id = new IdealConditionId();
        id.setMushroomId(1L);
        id.setDevelopmentStage("Fruiting");
        idealCondition.setId(id);
        idealCondition.setTemperatureHigh(27.0);
        idealCondition.setTemperatureLow(24.0);
        idealCondition.setHumidityHigh(80.0);
        idealCondition.setHumidityLow(70.0);

        dto = new IdealConditionDto();
        dto.setMushroomId(1L);
        dto.setDevelopmentStage("Fruiting");
        dto.setTempHigh(27.0);
        dto.setTempLow(24.0);
        dto.setHumidityHigh(80.0);
        dto.setHumidityLow(70.0);
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
}