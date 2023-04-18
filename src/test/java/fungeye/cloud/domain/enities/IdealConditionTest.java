package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IdealConditionTest {

    @Test
    public void testGetId() {
        IdealConditionId id = new IdealConditionId();
        IdealCondition condition = new IdealCondition();
        condition.setId(id);
        assertEquals(id, condition.getId());
    }

    @Test
    public void testGetMushroom() {
        Mushroom mushroom = new Mushroom();
        IdealCondition condition = new IdealCondition();
        condition.setMushroom(mushroom);
        assertEquals(mushroom, condition.getMushroom());
    }

    @Test
    public void testGetTemperatureHigh() {
        IdealCondition condition = new IdealCondition();
        Double temperatureHigh = 30.0;
        condition.setTemperatureHigh(temperatureHigh);
        assertEquals(temperatureHigh, condition.getTemperatureHigh());
    }

    @Test
    public void testGetTemperatureLow() {
        IdealCondition condition = new IdealCondition();
        Double temperatureLow = 10.0;
        condition.setTemperatureLow(temperatureLow);
        assertEquals(temperatureLow, condition.getTemperatureLow());
    }

    @Test
    public void testGetHumidityHigh() {
        IdealCondition condition = new IdealCondition();
        Double humidityHigh = 90.0;
        condition.setHumidityHigh(humidityHigh);
        assertEquals(humidityHigh, condition.getHumidityHigh());
    }

    @Test
    public void testGetHumidityLow() {
        IdealCondition condition = new IdealCondition();
        Double humidityLow = 50.0;
        condition.setHumidityLow(humidityLow);
        assertEquals(humidityLow, condition.getHumidityLow());
    }

}