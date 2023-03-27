package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class MeasuredConditionIdTest {

    @Test
    void testEqualsAndHashCode() {
        MeasuredConditionId id1 = new MeasuredConditionId();
        id1.setDateTime(Instant.ofEpochSecond(1234567890L));
        id1.setBoxId(1L);

        MeasuredConditionId id2 = new MeasuredConditionId();
        id2.setDateTime(Instant.ofEpochSecond(1234567890L));
        id2.setBoxId(1L);

        MeasuredConditionId id3 = new MeasuredConditionId();
        id3.setDateTime(Instant.ofEpochSecond(1234567891L));
        id3.setBoxId(1L);

        assertAll(
                // Test equality
                () -> assertEquals(id1, id2),
                () -> assertNotEquals(id1, id3),
                // Test hashCode consistency
                () -> assertEquals(id1.hashCode(), id2.hashCode()),
                () -> assertNotEquals(id1.hashCode(), id3.hashCode())
        );
    }

    @Test
    void testSettersAndGetters() {
        MeasuredConditionId id = new MeasuredConditionId();
        id.setDateTime(Instant.ofEpochSecond(1234567890L));
        id.setBoxId(1L);

        assertAll(
                () -> assertEquals(Instant.ofEpochSecond(1234567890L), id.getDateTime()),
                () -> assertEquals(1L, id.getBoxId())
        );
    }
}
