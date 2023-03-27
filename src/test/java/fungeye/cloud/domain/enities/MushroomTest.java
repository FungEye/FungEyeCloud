package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

public class MushroomTest {

    @Test
    public void testSetAndGetId() {
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        Assertions.assertEquals(1L, mushroom.getId());
    }

    @Test
    public void testSetAndGetName() {
        Mushroom mushroom = new Mushroom();
        mushroom.setName("Button Mushroom");
        Assertions.assertEquals("Button Mushroom", mushroom.getName());
    }

    @Test
    public void testSetAndGetDescription() {
        Mushroom mushroom = new Mushroom();
        mushroom.setDescription("A type of edible mushroom.");
        Assertions.assertEquals("A type of edible mushroom.", mushroom.getDescription());
    }

    @Test
    public void testSetAndGetGrow() {
        Mushroom mushroom = new Mushroom();
        Grow grow = new Grow();
        mushroom.setGrow(grow);
        Assertions.assertEquals(grow, mushroom.getGrow());
    }

    @Test
    public void testSetAndGetIdealConditions() {
        Mushroom mushroom = new Mushroom();
        IdealCondition idealCondition1 = new IdealCondition();
        IdealCondition idealCondition2 = new IdealCondition();
        Set<IdealCondition> idealConditions = new LinkedHashSet<>();
        idealConditions.add(idealCondition1);
        idealConditions.add(idealCondition2);
        mushroom.setIdealConditions(idealConditions);
        Assertions.assertEquals(idealConditions, mushroom.getIdealConditions());
    }
}
