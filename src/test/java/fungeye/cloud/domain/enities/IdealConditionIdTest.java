package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class IdealConditionIdTest {

    @Test
    void testEqualsAndHashCode() {
        IdealConditionId id1 = new IdealConditionId();
        id1.setMushroomId(1L);
        id1.setDevelopmentStage("Pinhead formation");

        IdealConditionId id2 = new IdealConditionId();
        id2.setMushroomId(1L);
        id2.setDevelopmentStage("Pinhead formation");

        IdealConditionId id3 = new IdealConditionId();
        id3.setMushroomId(1L);
        id3.setDevelopmentStage("Spawn run");

        IdealConditionId id4 = new IdealConditionId();
        id4.setMushroomId(2L);
        id4.setDevelopmentStage("Fruiting");

        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertNotEquals(id1, id4);
        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
        assertNotEquals(id1.hashCode(), id4.hashCode());
    }

    @Test
    void testGettersAndSetter() {
        IdealConditionId id1 = new IdealConditionId();
        id1.setMushroomId(1L);
        id1.setDevelopmentStage("Pinhead formation");
        assertEquals(1L, id1.getMushroomId());
        assertEquals("Pinhead formation", id1.getDevelopmentStage());
    }

}