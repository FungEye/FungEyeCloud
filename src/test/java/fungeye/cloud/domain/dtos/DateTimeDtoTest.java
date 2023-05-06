package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTimeDtoTest {

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
    public void testEqualsAndHashCode() {
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
    public void detailedTestEquals() {
        DateTimeDto dto1 = new DateTimeDto(2023, 5, 6, 10, 30, 0);
        DateTimeDto dto2 = new DateTimeDto(2023, 5, 6, 10, 30, 0);
        DateTimeDto dto3 = new DateTimeDto(2023, 5, 6, 10, 30, 0);
        DateTimeDto dto4 = new DateTimeDto(2023, 4, 6, 10, 30, 0);
        DateTimeDto dto5 = new DateTimeDto(2023, 5, 7, 10, 30, 0);
        DateTimeDto dto6 = new DateTimeDto(2023, 5, 6, 9, 30, 0);
        DateTimeDto dto7 = new DateTimeDto(2023, 5, 6, 10, 31, 0);
        DateTimeDto dto8 = new DateTimeDto(2023, 5, 6, 10, 30, 1);

        // reflexive
        assertTrue(dto1.equals(dto1));

        // symmetric
        assertTrue(dto1.equals(dto2));
        assertTrue(dto2.equals(dto1));

        // transitive
        assertTrue(dto1.equals(dto2));
        assertTrue(dto2.equals(dto3));
        assertTrue(dto1.equals(dto3));

        // consistent
        assertTrue(dto1.equals(dto2));
        assertTrue(dto1.equals(dto2));
        assertTrue(dto1.equals(dto2));

        // not equal
        assertFalse(dto1.equals(dto4));
        assertFalse(dto1.equals(dto5));
        assertFalse(dto1.equals(dto6));
        assertFalse(dto1.equals(dto7));
        assertFalse(dto1.equals(dto8));

        // null and other object
        assertFalse(dto1.equals(null));
        assertFalse(dto1.equals(new Object()));
    }
}
