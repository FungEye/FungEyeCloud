package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.GrowDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Mushroom;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GrowMapperTest {

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<GrowMapper> constructor = GrowMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testMapToGrowDto() {
        // Arrange
        Box box = new Box();
        box.setId(1L);

        Set<Mushroom> mushrooms = new HashSet<>();
        mushrooms.add(new Mushroom());
        mushrooms.add(new Mushroom());

        LocalDate dateStarted = LocalDate.of(2023, 5, 1);

        Grow grow = new Grow();
        grow.setId(2L);
        grow.setBox(box);
        grow.setIsActive(true);
        grow.setDateStarted(dateStarted);
        grow.setDevelopmentStage("Early");


        // Act
        GrowDto dto = GrowMapper.mapToGrowDto(grow);


        // Assert
        assertNotNull(dto);
        assertEquals(grow.getId(), dto.getId());
        assertEquals(box.getId(), dto.getBoxId());
        assertEquals(grow.getIsActive(), dto.isActive());
        assertEquals(dateStarted.getDayOfMonth(), dto.getDate().getDay());
        assertEquals(dateStarted.getMonthValue(), dto.getDate().getMonth());
        assertEquals(dateStarted.getYear(), dto.getDate().getYear());
        assertEquals(grow.getDevelopmentStage(), dto.getStage());
    }

    @Test
    void testMapToGrowDtoList() {
        // Arrange
        Box box1 = new Box();
        box1.setId(1L);
        Box box2 = new Box();
        box2.setId(2L);

        Set<Mushroom> mushrooms = new HashSet<>();
        mushrooms.add(new Mushroom());
        mushrooms.add(new Mushroom());

        LocalDate dateStarted = LocalDate.of(2023, 5, 1);

        Grow grow1 = new Grow();
        grow1.setId(2L);
        grow1.setBox(box1);
        grow1.setIsActive(true);
        grow1.setDateStarted(dateStarted);
        grow1.setDevelopmentStage("Early");

        Grow grow2 = new Grow();
        grow2.setId(2L);
        grow2.setBox(box1);
        grow2.setIsActive(true);
        grow2.setDateStarted(dateStarted);
        grow2.setDevelopmentStage("Early");

        Set<Grow> grows = new HashSet<>();
        grows.add(grow1);
        grows.add(grow2);

        // Act
        List<GrowDto> dtos = GrowMapper.mapToGrowDtoList(grows);

        // Assert
        assertNotNull(dtos);
        assertEquals(grows.size(), dtos.size());
        assertEquals(grow2.getId(), dtos.get(0).getId());
        assertEquals(grow2.getId(), dtos.get(1).getId());
    }
}
