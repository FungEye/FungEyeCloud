package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.enities.IdealCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MushroomUpdateDtoTest {

    private MushroomUpdateDto dto1;
    private MushroomUpdateDto dto2;


    @BeforeEach
    void setUp()
    {
        dto1 = new MushroomUpdateDto();
        dto2 = new MushroomUpdateDto();
    }

    @Test
    void setAndGetName()
    {
        dto1.setName("Fugly the Shitake");
        assertEquals("Fugly the Shitake", dto1.getName());
    }

    @Test
    void setAndGetDescription()
    {
        dto1.setDescription("The one and only");
        assertEquals("The one and only", dto1.getDescription());
    }

    @Test
    void setAndGetIdealConditions()
    {
        IdealCondition idealCondition1 = new IdealCondition();
        IdealCondition idealCondition2 = new IdealCondition();
        Set<IdealCondition> idealConditions = new LinkedHashSet<>();
        idealConditions.add(idealCondition1);
        idealConditions.add(idealCondition2);
        dto1.setIdealConditions(idealConditions);
        assertEquals(idealConditions, dto1.getIdealConditions());
    }

}

