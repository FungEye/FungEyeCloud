package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeasuredConditionTest {

    @Test
    void testGetId() {
        MeasuredConditionId id = new MeasuredConditionId();
        MeasuredCondition measuredCondition = new MeasuredCondition();
        measuredCondition.setId(id);
        assertEquals(id, measuredCondition.getId());
    }

    @Test
    void testGetBox() {
        Box box = new Box();
        box.setId(1L);
        MeasuredCondition measuredCondition = new MeasuredCondition();
        measuredCondition.setBox(box);
        assertEquals(box, measuredCondition.getBox());
    }

    @Test
    void testGetTemperature() {
        Double temperature = 22.5;
        MeasuredCondition measuredCondition = new MeasuredCondition();
        measuredCondition.setTemperature(temperature);
        assertEquals(temperature, measuredCondition.getTemperature());
    }

    @Test
    void testGetHumidity() {
        Double humidity = 75.0;
        MeasuredCondition measuredCondition = new MeasuredCondition();
        measuredCondition.setHumidity(humidity);
        assertEquals(humidity, measuredCondition.getHumidity());
    }

    @Test
    void testGetCO2() {
        Double co2 = 800.0;
        MeasuredCondition measuredCondition = new MeasuredCondition();
        measuredCondition.setCo2(co2);
        assertEquals(co2, measuredCondition.getCo2());
    }
    @Test
    void testGetLight() {
        Double light = 75.0;
        MeasuredCondition measuredCondition = new MeasuredCondition();
        measuredCondition.setLight(light);
        assertEquals(light, measuredCondition.getLight());
    }

}