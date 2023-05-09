package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DateTimeDtoTest {

    @Test
    void testConstructor() {
        DateTimeDto dateTime = new DateTimeDto(2022, 4, 14, 11, 30, 0);
        assertEquals(2022, dateTime.getYear());
        assertEquals(4, dateTime.getMonth());
        assertEquals(14, dateTime.getDay());
        assertEquals(11, dateTime.getHour());
        assertEquals(30, dateTime.getMinute());
        assertEquals(0, dateTime.getSecond());
    }

    @Test
    void testSettersAndGetters() {
        DateTimeDto dateTime = new DateTimeDto();
        dateTime.setYear(2023);
        dateTime.setMonth(5);
        dateTime.setDay(15);
        dateTime.setHour(12);
        dateTime.setMinute(30);
        dateTime.setSecond(0);

        assertEquals(2023, dateTime.getYear());
        assertEquals(5, dateTime.getMonth());
        assertEquals(15, dateTime.getDay());
        assertEquals(12, dateTime.getHour());
        assertEquals(30, dateTime.getMinute());
        assertEquals(0, dateTime.getSecond());
    }

    @Test
    void testEqualsAndHashCode() {
        DateTimeDto dateTimeDto1 = new DateTimeDto(2023, 5, 6, 11, 22, 33);
        DateTimeDto dateTimeDto2 = new DateTimeDto(2023, 5, 6, 11, 22, 33);
        DateTimeDto dateTimeDto3 = new DateTimeDto(2023, 5, 6, 11, 22, 34);

        // Test equality of objects with same properties
        assertEquals(dateTimeDto1, dateTimeDto2);
        assertEquals(dateTimeDto1.hashCode(), dateTimeDto2.hashCode());

        // Test inequality of objects with different properties
        assertNotEquals(dateTimeDto1, dateTimeDto3);
        assertNotEquals(dateTimeDto1.hashCode(), dateTimeDto3.hashCode());
    }

    @Test
    void detailedTestEquals() {
        DateTimeDto dto1 = new DateTimeDto(2023, 5, 6, 10, 30, 0);
        DateTimeDto dto2 = new DateTimeDto(2023, 5, 6, 10, 30, 0);
        DateTimeDto dto3 = new DateTimeDto(2023, 5, 6, 10, 30, 0);
        DateTimeDto dto4 = new DateTimeDto(2023, 4, 6, 10, 30, 0);
        DateTimeDto dto5 = new DateTimeDto(2023, 5, 7, 10, 30, 0);
        DateTimeDto dto6 = new DateTimeDto(2023, 5, 6, 9, 30, 0);
        DateTimeDto dto7 = new DateTimeDto(2023, 5, 6, 10, 31, 0);
        DateTimeDto dto8 = new DateTimeDto(2023, 5, 6, 10, 30, 1);
        DateTimeDto dto9 = new DateTimeDto(2022, 5, 6, 10, 30, 1);


        // reflexive
        assertEquals(dto1, dto1);

        // symmetric
        assertEquals(dto1, dto2);
        assertEquals(dto2, dto1);

        // transitive
        assertEquals(dto1, dto2);
        assertEquals(dto2, dto3);
        assertEquals(dto1, dto3);

        // consistent
        assertEquals(dto1, dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1, dto2);

        // not equal
        assertNotEquals(dto1, dto4);
        assertNotEquals(dto1, dto5);
        assertNotEquals(dto1, dto6);
        assertNotEquals(dto1, dto7);
        assertNotEquals(dto1, dto8);
        assertNotEquals(dto9, dto8);

        // null and other object
        assertNotEquals(null, dto1);
        assertNotEquals(dto1, new Object());
    }
}
