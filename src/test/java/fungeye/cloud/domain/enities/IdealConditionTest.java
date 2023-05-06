package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdealConditionTest {

    @Test
    void testGetId() {
        IdealConditionId id = new IdealConditionId();
        IdealCondition condition = new IdealCondition();
        condition.setId(id);
        assertEquals(id, condition.getId());
    }

    @Test
    void testGetMushroom() {
        Mushroom mushroom = new Mushroom();
        IdealCondition condition = new IdealCondition();
        condition.setMushroom(mushroom);
        assertEquals(mushroom, condition.getMushroom());
    }

    @Test
    void testGetTemperatureHigh() {
        IdealCondition condition = new IdealCondition();
        Double temperatureHigh = 30.0;
        condition.setTemperatureHigh(temperatureHigh);
        assertEquals(temperatureHigh, condition.getTemperatureHigh());
    }

    @Test
    void testGetTemperatureLow() {
        IdealCondition condition = new IdealCondition();
        Double temperatureLow = 10.0;
        condition.setTemperatureLow(temperatureLow);
        assertEquals(temperatureLow, condition.getTemperatureLow());
    }

    @Test
    void testGetHumidityHigh() {
        IdealCondition condition = new IdealCondition();
        Double humidityHigh = 90.0;
        condition.setHumidityHigh(humidityHigh);
        assertEquals(humidityHigh, condition.getHumidityHigh());
    }

    @Test
    void testGetHumidityLow() {
        IdealCondition condition = new IdealCondition();
        Double humidityLow = 50.0;
        condition.setHumidityLow(humidityLow);
        assertEquals(humidityLow, condition.getHumidityLow());
    }

}