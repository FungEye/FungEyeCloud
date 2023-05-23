package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdealConditionIdTest {

    @Test
    void testInvalidDevelopmentStage() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            IdealConditionId id = new IdealConditionId();
            id.setDevelopmentStage("Angsty teenage fungus");
        });

        Assertions.assertEquals("Not a valid development stage", thrown.getMessage());
    }

    @Test
    void testValidDevelopmentStage() {
        IdealConditionId id = new IdealConditionId();
        id.setDevelopmentStage("spawn run");

        assertEquals("spawn run", id.getDevelopmentStage());
    }

    @Test
    void testEqualsAndHashCode() {
        IdealConditionId id1 = new IdealConditionId();
        id1.setMushroomId(1L);
        id1.setDevelopmentStage("pinning");

        IdealConditionId id2 = new IdealConditionId();
        id2.setMushroomId(1L);
        id2.setDevelopmentStage("pinning");

        IdealConditionId id3 = new IdealConditionId();
        id3.setMushroomId(1L);
        id3.setDevelopmentStage("spawn run");

        IdealConditionId id4 = new IdealConditionId();
        id4.setMushroomId(2L);
        id4.setDevelopmentStage("fruiting");

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
        id1.setDevelopmentStage("pinning");
        assertEquals(1L, id1.getMushroomId());
        assertEquals("pinning", id1.getDevelopmentStage());
    }

}