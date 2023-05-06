package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeasuredConditionIdDtoTest {

    @Test
    void testConstructorAndGetters() {
        DateTimeDto dateTime = new DateTimeDto(2022, 1, 1, 0, 0, 0);
        Long boxId = 12345L;
        MeasuredConditionIdDto dto = new MeasuredConditionIdDto(boxId, dateTime);

        assertEquals(boxId, dto.getBoxId());
        assertEquals(dateTime, dto.getDateTime());
    }

    @Test
    void testSetters() {
        DateTimeDto dateTime1 = new DateTimeDto(2022, 1, 1, 0, 0, 0);
        DateTimeDto dateTime2 = new DateTimeDto(2022, 1, 2, 0, 0, 0);
        Long boxId1 = 12345L;
        Long boxId2 = 67890L;

        MeasuredConditionIdDto dto = new MeasuredConditionIdDto(boxId1, dateTime1);

        dto.setBoxId(boxId2);
        assertEquals(boxId2, dto.getBoxId());

        dto.setDateTime(dateTime2);
        assertEquals(dateTime2, dto.getDateTime());
    }
}
