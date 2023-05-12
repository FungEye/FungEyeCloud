package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.service.MushroomService;
import fungeye.cloud.service.mappers.MushroomMapper;
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
        mushroomCreationDTO.setUserId(0);

        MushroomDto mushroomDto = new MushroomDto();
        mushroomDto.setId(1L);
        mushroomDto.setName("Portobello");
        mushroomDto.setDescription("Large mushroom with a meaty texture.");
        mushroomDto.setUserId(0);

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

        when(service.getByMushroomId(mushroomId)).thenReturn(mushroomDto);

        ResponseEntity<MushroomDto> response = controller.getMushroomById(mushroomId);

        verify(service, times(1)).getByMushroomId(mushroomId);
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

        when(service.getAllDefault()).thenReturn(mushroomDtos);

        ResponseEntity<List<MushroomDto>> response = controller.getAllDefaultMushrooms();

        verify(service, times(1)).getAllDefault();
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mushroomDtos, response.getBody());
    }

    @Test
    void getCustomAndDefaultMushrooms()
    {
        UserEntity admin = new UserEntity();
        admin.setId(3);

        UserEntity user = new UserEntity();
        user.setId(2);

        Mushroom mushroom1 = new Mushroom();
        mushroom1.setId(1L);
        mushroom1.setName("Mushroom1");
        mushroom1.setDescription("Test mushroom 1");
        mushroom1.setUser(user);

        Mushroom mushroom2 = new Mushroom();
        mushroom2.setId(2L);
        mushroom2.setName("Mushroom2");
        mushroom2.setDescription("Test mushroom 2");
        mushroom2.setUser(user);

        Mushroom mushroom3 = new Mushroom();
        mushroom3.setId(3L);
        mushroom3.setName("Mushroom3");
        mushroom3.setDescription("Default mushroom 1");
        mushroom3.setUser(admin);


        List<MushroomDto> customDtos = new ArrayList<>();
        customDtos.add(MushroomMapper.mapToMushroomDto(mushroom1));
        customDtos.add(MushroomMapper.mapToMushroomDto(mushroom2));

        List<MushroomDto> defaultMushroomDtos = new ArrayList<>();
        defaultMushroomDtos.add(MushroomMapper.mapToMushroomDto(mushroom3));

        List<MushroomDto> expected = new ArrayList<>();
        expected.addAll(customDtos);
        expected.addAll(defaultMushroomDtos);

        when(service.getCustom(2)).thenReturn(customDtos);
        when(service.getAllDefault()).thenReturn(defaultMushroomDtos);


        ResponseEntity<List<MushroomDto>> response = controller.getDefaultAndCustom(2);

        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.FOUND, response.getStatusCode());

        verify(service, times(1)).getCustom(2);
        verify(service, times(1)).getAllDefault();
    }
}
