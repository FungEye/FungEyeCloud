package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.enities.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import static fungeye.cloud.service.mappers.BoxMapper.mapToBoxDto;
import static fungeye.cloud.service.mappers.BoxMapper.mapToBoxDtoList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BoxMapperTest {

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<BoxMapper> constructor = BoxMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testMapToSimpleDto() {
        // create a Box object
        Box box = new Box();
        box.setId(1L);

        // call the method to be tested
        BoxDto dto = BoxMapper.mapToSimpleDto(box);

        // check if the result is correct
        assertEquals(dto.getId(), box.getId());
    }

    @Test
    void testMapToBoxDto() {
        LocalDate mockDate = LocalDate.of(2023, 1,1);

        Box box = new Box();
        box.setId(1L);

        Set<Grow> grows = new HashSet<>();

        Grow grow = new Grow();
        grow.setBox(box);
        grow.setDateStarted(mockDate);
        grow.setIsActive(true);

        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        mushroom.setName("Mushroom");

        grow.setMushroom(mushroom);


        Set<MeasuredCondition> conditions = new LinkedHashSet<>();


        for (int i = 0; i < 10; i++) {
            MeasuredConditionId condId = new MeasuredConditionId();
            condId.setBoxId(1L);
            condId.setDateTime(Instant.now().plusSeconds(i * 10));
            MeasuredCondition cond = new MeasuredCondition();
            cond.setId(condId);
            cond.setBox(box);
            cond.setHumidity(200.0);
            cond.setLight(200.0);
            cond.setTemperature(20.0);
            cond.setCo2(2000.0);

            conditions.add(cond);
        }

        grows.add(grow);
        box.setGrows(grows);
        box.setMeasuredConditions(conditions);

        // call the method to be tested
        BoxDetailsDto dto = mapToBoxDto(box);

        // check if the result is correct
        assertEquals(dto.getId(), box.getId());
        assertEquals(dto.getGrows().size(), box.getGrows().size());
        assertEquals(dto.getConditions().size(), box.getMeasuredConditions().size());
    }

    @Test
    void testMapToBoxDtoList()
    {
        LocalDate mockDate = LocalDate.of(2023, 1,1);

        Mushroom mushroom1 = new Mushroom();
        mushroom1.setId(1L);
        Mushroom mushroom2 = new Mushroom();
        mushroom1.setId(2L);
        Mushroom mushroom3 = new Mushroom();
        mushroom1.setId(3L);
        Mushroom mushroom4 = new Mushroom();
        mushroom1.setId(4L);


        Box box1 = new Box();
        box1.setId(1L);
        Box box2 = new Box();
        box2.setId(2L);
        Box box3 = new Box();
        box3.setId(3L);

        Grow grow1 = new Grow();
        grow1.setBox(box1);
        grow1.setDateStarted(mockDate);
        grow1.setIsActive(false);
        grow1.setMushroom(mushroom1);
        Grow grow2 = new Grow();
        grow2.setBox(box1);
        grow2.setDateStarted(mockDate.plusDays(20));
        grow2.setIsActive(true);
        grow2.setMushroom(mushroom2);

        Grow grow3 = new Grow();
        grow3.setBox(box2);
        grow3.setDateStarted(mockDate);
        grow3.setIsActive(false);
        grow3.setMushroom(mushroom3);
        Grow grow4 = new Grow();
        grow4.setBox(box2);
        grow4.setDateStarted(mockDate.plusDays(20));
        grow4.setIsActive(true);
        grow4.setMushroom(mushroom4);

        Grow grow5 = new Grow();
        grow5.setBox(box3);
        grow5.setDateStarted(mockDate);
        grow5.setIsActive(false);
        grow5.setMushroom(mushroom3);
        Grow grow6 = new Grow();
        grow6.setBox(box3);
        grow6.setDateStarted(mockDate.plusDays(20));
        grow6.setIsActive(true);
        grow6.setMushroom(mushroom1);


        Set<Grow> grows1 = new HashSet<>();
        Set<Grow> grows2 = new HashSet<>();
        Set<Grow> grows3 = new HashSet<>();
        Set<MeasuredCondition> conditions1 = new LinkedHashSet<>();
        Set<MeasuredCondition> conditions2 = new LinkedHashSet<>();
        Set<MeasuredCondition> conditions3 = new LinkedHashSet<>();

        for (int i = 0; i < 10; i++) {
            MeasuredConditionId condId1 = new MeasuredConditionId();
            MeasuredConditionId condId2 = new MeasuredConditionId();
            MeasuredConditionId condId3 = new MeasuredConditionId();

            condId1.setBoxId(1L);
            condId1.setDateTime(Instant.now().plusSeconds(i * 10));
            condId2.setBoxId(2L);
            condId2.setDateTime(Instant.now().plusSeconds(i * 20));
            condId3.setBoxId(3L);
            condId3.setDateTime(Instant.now().plusSeconds(i * 30));

            MeasuredCondition cond1 = new MeasuredCondition();
            cond1.setId(condId1);
            cond1.setBox(box1);
            cond1.setHumidity(100.0 + i);
            cond1.setLight(100.0 + i);
            cond1.setTemperature(10.0 + i);
            cond1.setCo2(1000.0 + i);
            MeasuredCondition cond2 = new MeasuredCondition();
            cond2.setId(condId2);
            cond2.setBox(box2);
            cond2.setHumidity(200.0 + i);
            cond2.setLight(200.0 + i);
            cond2.setTemperature(20.0 + i);
            cond2.setCo2(2000.0 + i);
            MeasuredCondition cond3 = new MeasuredCondition();
            cond3.setId(condId3);
            cond3.setBox(box3);
            cond3.setHumidity(300.0 + i);
            cond3.setLight(300.0 + i);
            cond3.setTemperature(30.0 + i);
            cond3.setCo2(3000.0 + i);

            conditions1.add(cond1);
            conditions2.add(cond2);
            conditions3.add(cond3);
        }

        grows1.add(grow1);
        grows1.add(grow2);
        box1.setGrows(grows1);
        box1.setMeasuredConditions(conditions1);
        grows2.add(grow3);
        grows2.add(grow4);
        box2.setGrows(grows2);
        box2.setMeasuredConditions(conditions2);
        grows3.add(grow5);
        grows3.add(grow6);
        box3.setGrows(grows3);
        box3.setMeasuredConditions(conditions3);

        List<Box> boxes = new ArrayList<>();
        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);

        // Method to test
        List<BoxDetailsDto> result = mapToBoxDtoList(boxes);

        // Check if size is as expected
        assertEquals(3, result.size());
        // Check if ordering is as expected
        assertEquals(box1.getId(), result.get(0).getId());
        assertEquals(box2.getId(), result.get(1).getId());
        assertEquals(box3.getId(), result.get(2).getId());
        // Check "randomly" selected measurements from each box:
        assertEquals(100.0,result.get(0).getConditions().get(0).getHumidity());
        assertEquals(25.0,result.get(1).getConditions().get(5).getTemperature());
        assertEquals(3009.0,result.get(2).getConditions().get(9).getCo2());



    }

    @Test
    void testMapFromBoxDto() {
        // create a BoxDetailsDto object
        BoxDetailsDto dto = new BoxDetailsDto();
        dto.setId(1L);

        // call the method to be tested
        Box box = BoxMapper.mapFromBoxDto(dto);

        // check if the result is correct
        assertEquals(box.getId(), dto.getId());
    }
}
