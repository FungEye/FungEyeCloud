package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.grow.GrowIdMushroomNameDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Mushroom;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        Mushroom shroom1 = new Mushroom();
        shroom1.setId(1L);
        LocalDate dateStarted = LocalDate.of(2023, 5, 1);
        Grow grow = new Grow();
        grow.setId(2L);
        grow.setBox(box);
        grow.setIsActive(true);
        grow.setDateStarted(dateStarted);
        grow.setDevelopmentStage("spawn run");
        grow.setMushroom(shroom1);


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

        Mushroom shroom1 = new Mushroom();
        shroom1.setId(1L);
        Mushroom shroom2 = new Mushroom();
        shroom2.setId(2L);

        LocalDate dateStarted = LocalDate.of(2023, 5, 1);

        Grow grow1 = new Grow();
        grow1.setId(2L);
        grow1.setBox(box1);
        grow1.setIsActive(true);
        grow1.setDateStarted(dateStarted);
        grow1.setDevelopmentStage("spawn run");
        grow1.setMushroom(shroom1);

        Grow grow2 = new Grow();
        grow2.setId(2L);
        grow2.setBox(box1);
        grow2.setIsActive(true);
        grow2.setDateStarted(dateStarted);
        grow2.setDevelopmentStage("spawn run");
        grow2.setMushroom(shroom2);

        Set<Grow> grows = new HashSet<>();
        grows.add(grow1);
        grows.add(grow2);

        // Act
        List<GrowDto> dtos = GrowMapper.mapToGrowDtoList(grows);

        // Assert
        assertNotNull(dtos);
        assertEquals(grows.size(), dtos.size());
        assertEquals(grow1.getId(), dtos.get(0).getId());
    }

    @Test
    void testMapToGrowIdMushroomNameDto() {
        Grow grow = new Grow();
        grow.setId(1L);

        Mushroom mushroom = new Mushroom();
        mushroom.setName("Portobello");
        grow.setMushroom(mushroom);

        GrowIdMushroomNameDto expected = new GrowIdMushroomNameDto();
        expected.setMushroomName("Portobello");
        expected.setId(1L);

        GrowIdMushroomNameDto actual = GrowMapper.mapToGrowIdMushroomNameDto(grow);

        assertEquals(expected, actual);
    }

    @Test
    void testMapFromGrowIdMushroomNameDto() {
        GrowIdMushroomNameDto dto = new GrowIdMushroomNameDto();
        dto.setId(1L);
        dto.setMushroomName("Portobello");

        Grow expected = new Grow();
        expected.setId(1L);

        Mushroom mushroom = new Mushroom();
        mushroom.setName("Portobello");
        expected.setMushroom(mushroom);

        Grow actual = GrowMapper.mapFromGrowIdMushroomNameDto(dto);

        assertEquals(expected, actual);
    }

    @Test
    void testMapToGrowIdMushroomNameDtoList() {
        Grow grow = new Grow();
        grow.setId(1L);

        Mushroom mushroom = new Mushroom();
        mushroom.setName("Portobello");
        grow.setMushroom(mushroom);

        Set<Grow> grows = new HashSet<>();
        grows.add(grow);

        GrowIdMushroomNameDto expected1 = new GrowIdMushroomNameDto();
        expected1.setMushroomName("Portobello");
        expected1.setId(1L);

        List<GrowIdMushroomNameDto> expected = new ArrayList<>();
        expected.add(expected1);

        List<GrowIdMushroomNameDto> actual = GrowMapper.mapToGrowIdMushroomNameDtoList(grows);

        assertEquals(expected, actual);
    }

    @Test
    void testMapFromGrowIdMushroomNameDtoList() {
        GrowIdMushroomNameDto dto = new GrowIdMushroomNameDto();
        dto.setId(1L);
        dto.setMushroomName("Portobello");

        List<GrowIdMushroomNameDto> dtos = new ArrayList<>();
        dtos.add(dto);

        Grow expected1 = new Grow();
        expected1.setId(1L);

        Mushroom mushroom = new Mushroom();
        mushroom.setName("Portobello");
        expected1.setMushroom(mushroom);

        Set<Grow> expected = new HashSet<>();
        expected.add(expected1);

        Set<Grow> actual = GrowMapper.mapFromGrowIdMushroomNameDtoList(dtos);

        assertEquals(expected, actual);
    }
}
