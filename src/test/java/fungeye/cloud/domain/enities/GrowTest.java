package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrowTest {

    @Test
    public void testGetId() {
        Grow grow = new Grow();
        grow.setId(1L);
        assertEquals(Long.valueOf(1), grow.getId());
    }

    @Test
    public void testGetDateStarted() {
        Grow grow = new Grow();
        LocalDate dateStarted = LocalDate.of(2022, 12, 31);
        grow.setDateStarted(dateStarted);
        assertEquals(dateStarted, grow.getDateStarted());
    }

    @Test
    public void testGetDevelopmentStage() {
        Grow grow = new Grow();
        String developmentStage = "fruiting";
        grow.setDevelopmentStage(developmentStage);
        assertEquals(developmentStage, grow.getDevelopmentStage());
    }

    @Test
    public void testGetIsActive() {
        Grow grow = new Grow();
        Boolean isActive = true;
        grow.setIsActive(isActive);
        assertEquals(isActive, grow.getIsActive());
    }

    @Test
    public void testGetBox() {
        Grow grow = new Grow();
        Box box = new Box();
        grow.setBox(box);
        assertEquals(box, grow.getBox());
    }

    @Test
    public void testGetMushrooms() {
        Grow grow = new Grow();
        Mushroom mushroom = new Mushroom();
        Set<Mushroom> mushrooms = new LinkedHashSet<>();
        mushrooms.add(mushroom);
        grow.setMushrooms(mushrooms);
        assertEquals(mushrooms, grow.getMushrooms());
    }
}