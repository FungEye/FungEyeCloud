package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeasuredConditionDtoTest {

    @Test
    public void testGetAndSetId() {
        MeasuredConditionIdDto id = new MeasuredConditionIdDto();
        MeasuredConditionDto dto = new MeasuredConditionDto();
        dto.setId(id);
        assertEquals(id, dto.getId());
    }

    @Test
    public void testGetAndSetTemperature() {
        Double temperature = 25.0;
        MeasuredConditionDto dto = new MeasuredConditionDto();
        dto.setTemperature(temperature);
        assertEquals(temperature, dto.getTemperature());
    }

    @Test
    public void testGetAndSetHumidity() {
        Double humidity = 70.0;
        MeasuredConditionDto dto = new MeasuredConditionDto();
        dto.setHumidity(humidity);
        assertEquals(humidity, dto.getHumidity());
    }
}
