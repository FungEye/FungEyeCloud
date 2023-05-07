package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeasuredConditionDtoTest {

    @Test
    void testGetAndSetId() {
        MeasuredConditionIdDto id = new MeasuredConditionIdDto();
        MeasuredConditionDto dto = new MeasuredConditionDto();
        dto.setId(id);
        assertEquals(id, dto.getId());
    }

    @Test
    void testGetAndSetTemperature() {
        Double temperature = 25.0;
        MeasuredConditionDto dto = new MeasuredConditionDto();
        dto.setTemperature(temperature);
        assertEquals(temperature, dto.getTemperature());
    }

    @Test
    void testGetAndSetHumidity() {
        Double humidity = 70.0;
        MeasuredConditionDto dto = new MeasuredConditionDto();
        dto.setHumidity(humidity);
        assertEquals(humidity, dto.getHumidity());
    }
}
