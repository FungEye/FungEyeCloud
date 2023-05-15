package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdealConditionDtoTest {

    @Test
    void testSetDevelopmentStage() {
        IdealConditionDto idealConditionDto = new IdealConditionDto();

        // test valid stage
        idealConditionDto.setDevelopmentStage("Spawn run");
        Assertions.assertEquals("Spawn run", idealConditionDto.getDevelopmentStage());
    }

    @Test
    void testEqualsAndHashCode() {
        IdealConditionDto idealConditionDto1 = new IdealConditionDto();
        idealConditionDto1.setMushroomId(1L);
        idealConditionDto1.setDevelopmentStage("Spawn run");
        idealConditionDto1.setTempHigh(25.0);
        idealConditionDto1.setTempLow(20.0);
        idealConditionDto1.setHumidityHigh(80.0);
        idealConditionDto1.setHumidityLow(70.0);
        idealConditionDto1.setLightHigh(30.0);
        idealConditionDto1.setLightLow(20.5);
        idealConditionDto1.setCo2High(60.4);
        idealConditionDto1.setCo2Low(40.8);

        IdealConditionDto idealConditionDto2 = new IdealConditionDto();
        idealConditionDto2.setMushroomId(1L);
        idealConditionDto2.setDevelopmentStage("Spawn run");
        idealConditionDto2.setTempHigh(25.0);
        idealConditionDto2.setTempLow(20.0);
        idealConditionDto2.setHumidityHigh(80.0);
        idealConditionDto2.setHumidityLow(70.0);
        idealConditionDto2.setLightHigh(30.0);
        idealConditionDto2.setLightLow(20.5);
        idealConditionDto2.setCo2High(60.4);
        idealConditionDto2.setCo2Low(40.8);

        IdealConditionDto idealConditionDto3 = new IdealConditionDto();
        idealConditionDto3.setMushroomId(2L);
        idealConditionDto3.setDevelopmentStage("Fruiting");
        idealConditionDto3.setTempHigh(30.0);
        idealConditionDto3.setTempLow(25.0);
        idealConditionDto3.setHumidityHigh(90.0);
        idealConditionDto3.setHumidityLow(80.0);
        idealConditionDto3.setLightHigh(50.0);
        idealConditionDto3.setLightLow(30.5);
        idealConditionDto3.setCo2High(50.4);
        idealConditionDto3.setCo2Low(40.8);

        // test equals and hashCode
        Assertions.assertEquals(idealConditionDto1, idealConditionDto2);
        Assertions.assertNotEquals(idealConditionDto1, idealConditionDto3);
        Assertions.assertEquals(idealConditionDto1.hashCode(), idealConditionDto2.hashCode());
        Assertions.assertNotEquals(idealConditionDto1.hashCode(), idealConditionDto3.hashCode());
    }
}
