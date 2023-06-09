package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.date.DateTimeDto;
import fungeye.cloud.domain.dtos.date.SimpleDateDto;
import fungeye.cloud.domain.dtos.grow.GrowCreationDto;
import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.grow.GrowIdMushroomNameDto;
import fungeye.cloud.domain.dtos.grow.GrowUpdateDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.service.GrowService;
import fungeye.cloud.service.mappers.DateTimeMapper;
import fungeye.cloud.service.mappers.GrowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GrowControllerTest {
    @Mock
    private GrowService service;
    private GrowController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        controller = new GrowController(service);
    }

    @Test
    void testCreateGrow() throws Exception {

        // Arrange
        GrowCreationDto dto = new GrowCreationDto();
        dto.setBoxId(1L);
        dto.setMushroomId(1L);
        dto.setUsername("john");
        dto.setDevelopStage("spawn run");

        SimpleDateDto dateDto = new SimpleDateDto(2023, 5, 5);
        dto.setDate(dateDto);

        Grow growToSave = GrowMapper.mapFromCreationDto(dto);

        Grow savedGrow = GrowMapper.mapFromCreationDto(dto);
        savedGrow.setId(1L);
        savedGrow.setIsActive(true);

        GrowDto savedDto = GrowMapper.mapToGrowDto(savedGrow);

        Mockito.when(service.createGrow(dto)).thenReturn(savedDto);

        // Act
        ResponseEntity<GrowDto> response1 = controller.createGrow(dto);

        // Assert
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals(savedDto, response1.getBody());
    }

    @Test
    void testGetGrowById() throws Exception{

        DateTimeDto dateTimeDto = DateTimeMapper.mapToDateDto(LocalDate.now());

        Box box = new Box();
        box.setId(1L);

        GrowDto dto = new GrowDto();
        dto.setId(1L);
        dto.setDate(dateTimeDto);
        dto.setBoxId(1L);
        dto.setMushroomId(1L);
        dto.setStage("spawn run");
        dto.setActive(true);
        dto.setBoxId(box.getId());

        Grow grow = new Grow();
        grow.setId(1L);
        grow.setDateStarted(LocalDate.now());
        grow.setDevelopmentStage("spawn run");
        grow.setBox(box);
        grow.setIsActive(true);

        Mockito.when(service.getGrowById(grow.getId())).thenReturn(dto);

        ResponseEntity<GrowDto> actual = controller.getGrowById(grow.getId());

        assertEquals(dto, actual.getBody());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    void testGetAllGrowsByUsername() throws Exception {

        List<GrowDto> grows = new ArrayList<>();

        GrowDto dto1 = new GrowDto();
        dto1.setBoxId(1L);
        dto1.setMushroomId(1L);
        dto1.setStage("spawn run");
        dto1.setActive(true);

        DateTimeDto dateTimeDto = DateTimeMapper.mapToDateDto(LocalDate.now());
        dto1.setDate(dateTimeDto);

        GrowDto dto2 = new GrowDto();
        dto2.setBoxId(2L);
        dto2.setMushroomId(2L);
        dto2.setStage("spawn run");
        dto2.setActive(true);
        dto2.setDate(dateTimeDto);

        Mushroom mushroom1 = new Mushroom();
        mushroom1.setId(1L);
        Mushroom mushroom2 = new Mushroom();
        mushroom2.setId(2L);

        grows.add(dto1);
        grows.add(dto2);

        List<GrowIdMushroomNameDto> expectedIds = new ArrayList<>();

        GrowIdMushroomNameDto dtoWithMushId1 = new GrowIdMushroomNameDto();
        dtoWithMushId1.setId(dto1.getId());
        dtoWithMushId1.setMushroomId(mushroom1.getId());

        GrowIdMushroomNameDto dtoWithMushId2 = new GrowIdMushroomNameDto();
        dtoWithMushId2.setId(dto2.getId());
        dtoWithMushId2.setMushroomId(mushroom2.getId());

        expectedIds.add(dtoWithMushId1);
        expectedIds.add(dtoWithMushId2);

        List<Grow> convertedGrows = GrowMapper.mapFromGrowDtoList(grows);

        Mockito.when(service.getAllGrowsByUsername("john")).thenReturn(expectedIds);

        ResponseEntity<List<GrowIdMushroomNameDto>> response1 = controller.getGrowsByUsername("john");

        assertEquals(expectedIds, response1.getBody());
        assertEquals(HttpStatus.OK, response1.getStatusCode());
    }

    @Test
    void testUpdateGrow() throws Exception {

        Grow initial = new Grow();
        initial.setId(1L);
        initial.setIsActive(true);
        initial.setDevelopmentStage("fruiting");
        initial.setDateStarted(LocalDate.of(2023, 5, 5));

        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        initial.setMushroom(mushroom);

        Box box = new Box();
        box.setId(1L);
        initial.setBox(box);

        GrowUpdateDto update = new GrowUpdateDto();
        update.setId(1L);
        update.setIsActive(false);
        update.setDevelopStage("fruiting");

        GrowDto dto = GrowMapper.mapToGrowDto(initial);
        dto.setActive(false);
        dto.setStage("fruiting");

        Mockito.when(service.updateGrow(update)).thenReturn(dto);

        ResponseEntity<GrowDto> response = controller.updateGrow(update);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testEndGrow() throws Exception {

        Grow grow = new Grow();
        grow.setId(1L);
        grow.setIsActive(true);
        grow.setDevelopmentStage("fruiting");
        grow.setDateStarted(LocalDate.of(2023, 5, 5));

        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        grow.setMushroom(mushroom);

        Box box = new Box();
        box.setId(1L);
        grow.setBox(box);

        GrowDto dto = GrowMapper.mapToGrowDto(grow);
        dto.setActive(false);

        Mockito.when(service.endGrow(1L)).thenReturn(dto);

        ResponseEntity<GrowDto> response = controller.endGrow(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());

        verify(service, times(1)).endGrow(1L);
    }
}
