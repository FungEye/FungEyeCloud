package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Test
    void testEqualsSameObject() {
        IdealCondition idealCondition = new IdealCondition();
        assertEquals(idealCondition, idealCondition);
    }

    @Test
    void testEqualsNull() {
        IdealCondition idealCondition = new IdealCondition();
        assertNotEquals(null, idealCondition);
    }

    @Test
    void testEqualsDifferentClass() {
        IdealCondition idealCondition = new IdealCondition();
        assertNotEquals(idealCondition, new Object());
    }

    @Test
    void testEqualsEqualObjects() {
        IdealConditionId id = new IdealConditionId(1L, "Fruiting");
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        IdealCondition idealCondition1 = new IdealCondition(id, mushroom, 25.0, 20.0, 80.0, 70.0, 800.0, 200.0, 1000.0, 200.0);
        IdealCondition idealCondition2 = new IdealCondition(id, mushroom, 25.0, 20.0, 80.0, 70.0, 800.0, 200.0, 1000.0, 200.0);
        assertEquals(idealCondition1, idealCondition2);
    }

    @Test
    void testEqualsNotEqualObjects() {
        IdealConditionId id1 = new IdealConditionId(1L, "Fruiting");
        IdealConditionId id2 = new IdealConditionId(2L, "Fruiting");
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        IdealCondition idealCondition1 = new IdealCondition(id1, mushroom, 25.0, 20.0, 80.0, 70.0, 800.0, 200.0, 1000.0, 200.0);
        IdealCondition idealCondition2 = new IdealCondition(id2, mushroom, 25.0, 20.0, 80.0, 70.0, 800.0, 200.0, 1000.0, 200.0);
        assertNotEquals(idealCondition1, idealCondition2);
    }

    @Test
    void testHashCodeEqualObjects() {
        IdealConditionId id = new IdealConditionId(1L, "Fruiting");
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        IdealCondition idealCondition1 = new IdealCondition(id, mushroom, 25.0, 20.0, 80.0, 70.0, 800.0, 200.0, 1000.0, 200.0);
        IdealCondition idealCondition2 = new IdealCondition(id, mushroom, 25.0, 20.0, 80.0, 70.0, 800.0, 200.0, 1000.0, 200.0);
        assertEquals(idealCondition1.hashCode(), idealCondition2.hashCode());
    }

    @Test
    void testHashCodeNotEqualObjects() {
        IdealConditionId id1 = new IdealConditionId(1L, "Fruiting");
        IdealConditionId id2 = new IdealConditionId(2L, "Fruiting");
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        IdealCondition idealCondition1 = new IdealCondition(id1, mushroom, 25.0, 20.0, 80.0, 70.0, 800.0, 200.0, 1000.0, 200.0);
        IdealCondition idealCondition2 = new IdealCondition(id2, mushroom, 25.0, 20.0, 80.0, 70.0, 800.0, 200.0, 1000.0, 200.0);
        assertNotEquals(idealCondition1.hashCode(), idealCondition2.hashCode());
    }

    @Test
    public void testToString() {
        // Arrange
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        mushroom.setName("Shiitake");
        IdealConditionId id = new IdealConditionId(1L, "Fruiting");
        IdealCondition idealCondition = new IdealCondition();
        idealCondition.setId(id);
        idealCondition.setMushroom(mushroom);
        idealCondition.setTemperatureHigh(25.0);
        idealCondition.setTemperatureLow(20.0);
        idealCondition.setHumidityHigh(80.0);
        idealCondition.setHumidityLow(70.0);
        idealCondition.setCo2High(1000.0);
        idealCondition.setCo2Low(200.0);
        idealCondition.setLightHigh(1000.0);
        idealCondition.setLightLow(100.0);

        // Act
        String actualString = idealCondition.toString();

        // Assert
        String expectedString = "IdealCondition{id=IdealConditionId{mushroomId=1, developmentStage='Fruiting'}," +
                " mushroom=Mushroom{id=1, name='Shiitake', description='null', origin='null', idealConditions=[]}," +
                " temperatureHigh=25.0, temperatureLow=20.0, humidityHigh=80.0, humidityLow=70.0," +
                " co2High=1000.0, co2Low=200.0, lightHigh=1000.0, lightLow=100.0}";
        assertEquals(expectedString, actualString);
    }

}