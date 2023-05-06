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
    void testEquals() {
        DateTimeDto dateTime1 = new DateTimeDto();
        dateTime1.setYear(2023);
        dateTime1.setMonth(5);
        dateTime1.setDay(15);
        dateTime1.setHour(12);
        dateTime1.setMinute(30);
        dateTime1.setSecond(0);

        DateTimeDto dateTime2 = new DateTimeDto();
        dateTime2.setYear(2023);
        dateTime2.setMonth(5);
        dateTime2.setDay(15);
        dateTime2.setHour(12);
        dateTime2.setMinute(30);
        dateTime2.setSecond(0);

        assertEquals(dateTime1, dateTime2);
    }
}
