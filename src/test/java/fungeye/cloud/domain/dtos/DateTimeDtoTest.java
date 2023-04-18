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
}
