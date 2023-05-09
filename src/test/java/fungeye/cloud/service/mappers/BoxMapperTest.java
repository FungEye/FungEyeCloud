package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BoxMapperTest {

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<BoxMapper> constructor = BoxMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testMapToSimpleDto() {
        // create a Box object
        Box box = new Box();
        box.setId(1L);

        // call the method to be tested
        BoxDto dto = BoxMapper.mapToSimpleDto(box);

        // check if the result is correct
        assertEquals(dto.getId(), box.getId());
    }

    @Test
    void testMapToBoxDto() {
        // create a Box object with related Grows and MeasuredConditions
        Box box = new Box();
        box.setId(1L);
        Set<Grow> grows = new HashSet<>();
        Grow mockGrow = mock(Grow.class);
        mockGrow.setBox(box);
        LocalDate mockDate = LocalDate.of(2023, 5, 6);
        when(mockGrow.getDateStarted()).thenReturn(mockDate);
        grows.add(mockGrow);
        Set<MeasuredCondition> measuredConditions = new HashSet<>();
        MeasuredCondition mockCondition = mock(MeasuredCondition.class);
        MeasuredConditionId mockCondId = mock(MeasuredConditionId.class);
        Instant mockInstant = Instant.now();
        mockCondId.setBoxId(1L);
        mockCondId.setDateTime(mockInstant);
        mockCondition.setId(mockCondId);
        when(mockCondition.getId()).thenReturn(mockCondId);
        when(mockCondId.getDateTime()).thenReturn(mockInstant);
        when(mockGrow.getBox()).thenReturn(box);
        measuredConditions.add(mockCondition);
        box.setGrows(grows);
        box.setMeasuredConditions(measuredConditions);

        // call the method to be tested
        BoxDetailsDto dto = BoxMapper.mapToBoxDto(box);

        // check if the result is correct
        assertEquals(dto.getId(), box.getId());
        assertEquals(dto.getGrows().size(), box.getGrows().size());
        assertEquals(dto.getConditions().size(), box.getMeasuredConditions().size());
    }

    @Test
    void testMapFromBoxDto() {
        // create a BoxDetailsDto object
        BoxDetailsDto dto = new BoxDetailsDto();
        dto.setId(1L);

        // call the method to be tested
        Box box = BoxMapper.mapFromBoxDto(dto);

        // check if the result is correct
        assertEquals(box.getId(), dto.getId());
    }
}
