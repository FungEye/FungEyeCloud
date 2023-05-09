package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.DateTimeDto;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateTimeMapperTest {

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<DateTimeMapper> constructor = DateTimeMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }
    @Test
    void testMapToDateDtoWithInstant() {
        // Given
        Instant instant = Instant.parse("2023-05-07T09:25:30.000Z");
        DateTimeDto expectedDto = new DateTimeDto();
        expectedDto.setDay(7);
        expectedDto.setMonth(5);
        expectedDto.setYear(2023);
        expectedDto.setHour(9);
        expectedDto.setMinute(25);
        expectedDto.setSecond(30);

        // When
        DateTimeDto actualDto = DateTimeMapper.mapToDateDto(instant);

        // Then
        assertEquals(expectedDto.getDay(), actualDto.getDay());
        assertEquals(expectedDto.getMonth(), actualDto.getMonth());
        assertEquals(expectedDto.getYear(), actualDto.getYear());
        assertEquals(expectedDto.getHour(), actualDto.getHour());
        assertEquals(expectedDto.getMinute(), actualDto.getMinute());
        assertEquals(expectedDto.getSecond(), actualDto.getSecond());
    }

    @Test
    void testMapToDateDtoWithLocalDate() {
        // Given
        LocalDate localDate = LocalDate.of(2023, 5, 7);
        DateTimeDto expectedDto = new DateTimeDto();
        expectedDto.setDay(7);
        expectedDto.setMonth(5);
        expectedDto.setYear(2023);

        // When
        DateTimeDto actualDto = DateTimeMapper.mapToDateDto(localDate);

        // Then
        assertEquals(expectedDto.getDay(), actualDto.getDay());
        assertEquals(expectedDto.getMonth(), actualDto.getMonth());
        assertEquals(expectedDto.getYear(), actualDto.getYear());
    }

    @Test
    void testMapToInstant() {
        // Given
        DateTimeDto dto = new DateTimeDto();
        dto.setDay(7);
        dto.setMonth(5);
        dto.setYear(2023);
        dto.setHour(9);
        dto.setMinute(25);
        dto.setSecond(30);
        Instant expectedInstant = Instant.parse("2023-05-07T09:25:30.000Z");

        // When
        Instant actualInstant = DateTimeMapper.mapToInstant(dto);

        // Then
        assertEquals(expectedInstant, actualInstant);
    }
}
