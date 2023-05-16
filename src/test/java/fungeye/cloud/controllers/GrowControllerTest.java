package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GrowControllerTest {
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
        dto.setDevelopStage("Spawn run");

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
    void testGetAllGrowsByUsername() throws Exception {
        List<GrowDto> grows = new ArrayList<>();

        GrowDto dto1 = new GrowDto();
        dto1.setBoxId(1L);
        dto1.setMushroomId(1L);
        dto1.setStage("Spawn run");
        dto1.setActive(true);

        DateTimeDto dateTimeDto = DateTimeMapper.mapToDateDto(LocalDate.now());
        dto1.setDate(dateTimeDto);

        GrowDto dto2 = new GrowDto();
        dto2.setBoxId(2L);
        dto2.setMushroomId(2L);
        dto2.setStage("Spawn run");
        dto2.setActive(true);

        dto2.setDate(dateTimeDto);

        grows.add(dto1);
        grows.add(dto2);

        List<GrowIdDto> expectedIds = new ArrayList<>();
        GrowIdDto id1 = new GrowIdDto();
        id1.setId(dto1.getId());
        GrowIdDto id2 = new GrowIdDto();
        id2.setId(dto2.getId());
        expectedIds.add(id1);
        expectedIds.add(id2);

        List<Grow> convertedGrows = GrowMapper.mapFromGrowDtoList(grows);

        Mockito.when(service.getAllGrowsByUsername("john")).thenReturn(expectedIds);

        ResponseEntity<List<GrowIdDto>> response1 = controller.getGrowsByUsername("john");

        assertEquals(expectedIds, response1.getBody());
        assertEquals(HttpStatus.OK, response1.getStatusCode());
    }

    @Test
    void testUpdateGrow() throws Exception {
        Grow initial = new Grow();
        initial.setId(1L);
        initial.setIsActive(true);
        initial.setDevelopmentStage("Fruiting");
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
        update.setDevelopStage("Fruiting");

        GrowDto dto = GrowMapper.mapToGrowDto(initial);
        dto.setActive(false);
        dto.setStage("Fruiting");

        Mockito.when(service.updateGrow(update)).thenReturn(dto);

        ResponseEntity<GrowDto> response = controller.updateGrow(update);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }
}
