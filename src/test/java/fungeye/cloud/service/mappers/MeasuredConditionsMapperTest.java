package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.date.DateTimeDto;
import fungeye.cloud.domain.dtos.measured.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.measured.MeasuredConditionIdDto;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MeasuredConditionsMapperTest {

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<MeasuredConditionsMapper> constructor = MeasuredConditionsMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testMapToIdDto() {
        MeasuredConditionId conditionId = new MeasuredConditionId();
        conditionId.setBoxId(1L);
        conditionId.setDateTime(Instant.now());

        MeasuredConditionIdDto expectedDto = new MeasuredConditionIdDto();
        expectedDto.setBoxId(1L);
        expectedDto.setDateTime(new DateTimeDto());

        MeasuredConditionIdDto resultDto = MeasuredConditionsMapper.mapToIdDto(conditionId);

        assertEquals(expectedDto.getBoxId(), resultDto.getBoxId());
    }

    @Test
    void testMapToDto() {
        MeasuredCondition condition = new MeasuredCondition();
        MeasuredConditionId conditionId = new MeasuredConditionId();
        conditionId.setBoxId(1L);
        conditionId.setDateTime(Instant.now());
        condition.setId(conditionId);
        condition.setTemperature(25.5);
        condition.setHumidity(70.0);

        MeasuredConditionDto expectedDto = new MeasuredConditionDto();
        MeasuredConditionIdDto conditionIdDto = new MeasuredConditionIdDto();
        conditionIdDto.setBoxId(1L);
        conditionIdDto.setDateTime(new DateTimeDto());
        expectedDto.setId(conditionIdDto);
        expectedDto.setTemperature(25.5);
        expectedDto.setHumidity(70.0);

        MeasuredConditionDto resultDto = MeasuredConditionsMapper.mapToDto(condition);

        assertEquals(expectedDto.getTemperature(), resultDto.getTemperature());
        assertEquals(expectedDto.getHumidity(), resultDto.getHumidity());
    }
}