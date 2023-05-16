package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrowTest {

    @Test
    void testGetId() {
        Grow grow = new Grow();
        grow.setId(1L);
        assertEquals(Long.valueOf(1), grow.getId());
    }

    @Test
    void testGetDateStarted() {
        Grow grow = new Grow();
        LocalDate dateStarted = LocalDate.of(2022, 12, 31);
        grow.setDateStarted(dateStarted);
        assertEquals(dateStarted, grow.getDateStarted());
    }

    @Test
    void testGetDevelopmentStage() {
        Grow grow = new Grow();
        String developmentStage = "fruiting";
        grow.setDevelopmentStage(developmentStage);
        assertEquals(developmentStage, grow.getDevelopmentStage());
    }

    @Test
    void testGetIsActive() {
        Grow grow = new Grow();
        Boolean isActive = true;
        grow.setIsActive(isActive);
        assertEquals(isActive, grow.getIsActive());
    }

    @Test
    void testGetBox() {
        Grow grow = new Grow();
        Box box = new Box();
        grow.setBox(box);
        assertEquals(box, grow.getBox());
    }

    @Test
    void testGetMushroom() {
        Grow grow = new Grow();
        Mushroom mushroom = new Mushroom();

        grow.setMushroom(mushroom);
        assertEquals(mushroom, grow.getMushroom());
    }

    @Test
    void testGetHarvests() {
        Grow grow = new Grow();
        Set<Harvest> harvests = new LinkedHashSet<>();
        for (int i = 0; i < 4; i++) {
            Harvest harvest = new Harvest();
            harvest.setGrow(grow);
            harvest.setWeight(i * 2.08);
            harvest.setId((long)i+1);
            harvest.setMushroom(new Mushroom());
            harvests.add(harvest);
        }
        grow.setHarvests(harvests);
        assertEquals(harvests, grow.getHarvests());
    }
}