package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class IdealConditionIdTest {

    @Test
    void testEqualsAndHashCode() {
        IdealConditionId id1 = new IdealConditionId();
        id1.setMushroomId(1L);
        id1.setDevelopmentStage("stage1");

        IdealConditionId id2 = new IdealConditionId();
        id2.setMushroomId(1L);
        id2.setDevelopmentStage("stage1");

        IdealConditionId id3 = new IdealConditionId();
        id3.setMushroomId(1L);
        id3.setDevelopmentStage("stage2");

        IdealConditionId id4 = new IdealConditionId();
        id4.setMushroomId(2L);
        id4.setDevelopmentStage("stage1");

        assertEquals(id1, id2);
        assertNotEquals(id1, id3);
        assertNotEquals(id1, id4);
        assertEquals(id1.hashCode(), id2.hashCode());
        assertNotEquals(id1.hashCode(), id3.hashCode());
        assertNotEquals(id1.hashCode(), id4.hashCode());
    }

}