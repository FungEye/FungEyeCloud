package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.service.MushroomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MushroomControllerTest {

    @Mock
    private MushroomService service;

    private MushroomController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new MushroomController(service);
    }

    @Test
    void createMushroom_shouldCreateAndReturnMushroomDto() {
        MushroomCreationDTO mushroomCreationDTO = new MushroomCreationDTO();
        mushroomCreationDTO.setName("Portobello");
        mushroomCreationDTO.setDescription("Large mushroom with a meaty texture.");

        MushroomDto mushroomDto = new MushroomDto();
        mushroomDto.setId(1L);
        mushroomDto.setName("Portobello");
        mushroomDto.setDescription("Large mushroom with a meaty texture.");

        when(service.createMushroom(mushroomCreationDTO)).thenReturn(mushroomDto);

        ResponseEntity<MushroomDto> response = controller.createMushroom(mushroomCreationDTO);

        verify(service, times(1)).createMushroom(mushroomCreationDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mushroomDto, response.getBody());
    }

    @Test
    void getMushroomById_shouldReturnMushroomDto() {
        Long mushroomId = 1L;

        MushroomDto mushroomDto = new MushroomDto();
        mushroomDto.setId(mushroomId);
        mushroomDto.setName("Portobello");
        mushroomDto.setDescription("Large mushroom with a meaty texture.");

        when(service.getById(mushroomId)).thenReturn(mushroomDto);

        ResponseEntity<MushroomDto> response = controller.getMushroomById(mushroomId);

        verify(service, times(1)).getById(mushroomId);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mushroomDto, response.getBody());
    }

    @Test
    void getAllMushrooms_shouldReturnListOfMushroomDtos() {
        List<MushroomDto> mushroomDtos = new ArrayList<>();
        MushroomDto mushroomDto1 = new MushroomDto();
        mushroomDto1.setId(1L);
        mushroomDto1.setName("Portobello");
        mushroomDto1.setDescription("Large mushroom with a meaty texture.");
        mushroomDtos.add(mushroomDto1);

        MushroomDto mushroomDto2 = new MushroomDto();
        mushroomDto2.setId(2L);
        mushroomDto2.setName("Shiitake");
        mushroomDto2.setDescription("Popular mushroom in East Asia.");
        mushroomDtos.add(mushroomDto2);

        when(service.getAll()).thenReturn(mushroomDtos);

        ResponseEntity<List<MushroomDto>> response = controller.getAllMushrooms();

        verify(service, times(1)).getAll();
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mushroomDtos, response.getBody());
    }
}
