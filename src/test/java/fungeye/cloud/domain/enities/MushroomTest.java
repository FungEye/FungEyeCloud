package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MushroomTest {

    @Test
    void testSetAndGetId() {
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        assertEquals(1L, mushroom.getId());
    }

    @Test
    void testSetAndGetName() {
        Mushroom mushroom = new Mushroom();
        mushroom.setName("Button Mushroom");
        assertEquals("Button Mushroom", mushroom.getName());
    }

    @Test
    void testSetAndGetDescription() {
        Mushroom mushroom = new Mushroom();
        mushroom.setDescription("A type of edible mushroom.");
        assertEquals("A type of edible mushroom.", mushroom.getDescription());
    }

    @Test
    void testSetAndGetIdealConditions() {
        Mushroom mushroom = new Mushroom();
        IdealCondition idealCondition1 = new IdealCondition();
        IdealCondition idealCondition2 = new IdealCondition();
        Set<IdealCondition> idealConditions = new LinkedHashSet<>();
        idealConditions.add(idealCondition1);
        idealConditions.add(idealCondition2);
        mushroom.setIdealConditions(idealConditions);
        assertEquals(idealConditions, mushroom.getIdealConditions());
    }

    @Test
    public void testToString() {
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        mushroom.setName("Button Mushroom");
        mushroom.setDescription("A common edible mushroom");
        String expected = "Mushroom{id=1, name='Button Mushroom', description='A common edible mushroom', idealConditions=[]}";
        String actual = mushroom.toString();
        assertEquals(expected, actual);
    }
}
