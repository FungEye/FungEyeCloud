package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HarvestTest {

    private Harvest harvest;

    @Mock
    private Mushroom mushroom;

    @Mock
    private Grow grow;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        harvest = new Harvest();
    }

    @Test
    void getAndSetId() {
        harvest.setId(1L);
        assertEquals(1L, harvest.getId());
    }

    @Test
    void getAndSetMushroom() {
        harvest.setMushroom(mushroom);
        assertEquals(mushroom, harvest.getMushroom());
    }

    @Test
    void getAndSetGrow() {
        harvest.setGrow(grow);
        assertEquals(grow, harvest.getGrow());
    }

    @Test
    void getAndSetWeight() {
        harvest.setWeight(22.5);
        assertEquals(22.5, harvest.getWeight());
    }

    @Test
    void getAndSetDateHarvested() {
        harvest.setDateHarvested(LocalDate.now());
        assertEquals(LocalDate.now(), harvest.getDateHarvested());
    }

    @Test
    void testEqualsWhenTrue() {
        harvest.setId(1L);
        harvest.setMushroom(mushroom);
        harvest.setDateHarvested(LocalDate.of(2023,5,12));
        harvest.setGrow(grow);

        Harvest harvest2 = new Harvest();
        harvest2.setId(1L);
        harvest2.setMushroom(mushroom);
        harvest2.setDateHarvested(LocalDate.of(2023,5,12));
        harvest2.setGrow(grow);

        assertEquals(harvest, harvest2);
    }

    @Test
    void testEqualsWhenNotTrue() {
        harvest.setId(1L);
        harvest.setMushroom(mushroom);
        harvest.setDateHarvested(LocalDate.of(2023,5,12));
        harvest.setGrow(grow);

        Harvest harvest2 = new Harvest();
        harvest2.setId(3L);
        harvest2.setMushroom(mushroom);
        harvest2.setDateHarvested(LocalDate.of(2023,6,12));
        harvest2.setGrow(grow);

        assertNotEquals(harvest, harvest2);
    }

    @Test
    void testEqualsWhenComparedToNull() {
        harvest.setId(1L);
        harvest.setMushroom(mushroom);
        harvest.setDateHarvested(LocalDate.of(2023, 5, 12));
        harvest.setGrow(grow);

        assertNotEquals(null, harvest);
    }

    @Test
    void testEqualsWhenComparedToOtherObject() {
        harvest.setId(1L);
        harvest.setMushroom(mushroom);
        harvest.setDateHarvested(LocalDate.of(2023, 5, 12));
        harvest.setGrow(grow);

        assertNotEquals(grow, harvest);
    }

    @Test
    void testHashCodeWhenEqualObjects() {
        harvest.setId(1L);
        harvest.setMushroom(mushroom);
        harvest.setDateHarvested(LocalDate.of(2023, 5, 12));
        harvest.setGrow(grow);

        Harvest harvest2 = new Harvest();
        harvest2.setId(1L);
        harvest2.setMushroom(mushroom);
        harvest2.setDateHarvested(LocalDate.of(2023,5,12));
        harvest2.setGrow(grow);

        assertEquals(harvest.hashCode(), harvest2.hashCode());
    }

    @Test
    void testHashcodeWhenNotEqual() {
        harvest.setId(1L);
        harvest.setMushroom(mushroom);
        harvest.setDateHarvested(LocalDate.of(2023,5,12));
        harvest.setGrow(grow);

        Harvest harvest2 = new Harvest();
        harvest2.setId(3L);
        harvest2.setMushroom(mushroom);
        harvest2.setDateHarvested(LocalDate.of(2023,6,12));
        harvest2.setGrow(grow);

        assertNotEquals(harvest.hashCode(), harvest2.hashCode());
    }
}