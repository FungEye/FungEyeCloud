package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.*;
import fungeye.cloud.domain.enities.IdealCondition;
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
import org.springframework.security.core.userdetails.User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MushroomControllerTest {

    @Mock
    private MushroomService service;

    private MushroomController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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

        ResponseEntity<MushroomDto> response1 = controller.createMushroom(mushroomCreationDTO);
        ResponseEntity<MushroomDto> response2 = controller.createCustomMushroom(mushroomCreationDTO);

        verify(service, times(2)).createMushroom(mushroomCreationDTO);
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals(HttpStatus.CREATED, response2.getStatusCode());
        assertEquals(mushroomDto, response1.getBody());
        assertEquals(mushroomDto, response2.getBody());
    }

    @Test
    void createDefaultMushroom_shouldCreateAndReturnMushroomDto() {
        DefaultMushroomCreationDto mushroomCreationDTO = new DefaultMushroomCreationDto();
        mushroomCreationDTO.setName("Portobello");
        mushroomCreationDTO.setDescription("Large mushroom with a meaty texture.");

        MushroomDto mushroomDto = new MushroomDto();
        mushroomDto.setId(1L);
        mushroomDto.setName("Portobello");
        mushroomDto.setDescription("Large mushroom with a meaty texture.");
        mushroomDto.setUserId(3);

        when(service.createDefaultMushroom(mushroomCreationDTO)).thenReturn(mushroomDto);

        ResponseEntity<MushroomDto> response1 = controller.createDefaultMushroom(mushroomCreationDTO);

        verify(service, times(1)).createDefaultMushroom(mushroomCreationDTO);
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals(mushroomDto, response1.getBody());
    }

    @Test
    void createCustomMushroom_shouldCreateAndReturnMushroomDto() {
        CustomMushroomCreationDto mushroomCreationDTO = new CustomMushroomCreationDto();
        mushroomCreationDTO.setName("Portobello");
        mushroomCreationDTO.setDescription("Large mushroom with a meaty texture.");
        mushroomCreationDTO.setUsername("john");

        MushroomDto mushroomDto = new MushroomDto();
        mushroomDto.setId(1L);
        mushroomDto.setName("Portobello");
        mushroomDto.setDescription("Large mushroom with a meaty texture.");
        mushroomDto.setUserId(2);

        UserEntity user = new UserEntity();
        user.setId(2);
        user.setUsername("john");

        when(service.createCustomMushroom(mushroomCreationDTO)).thenReturn(mushroomDto);

        ResponseEntity<MushroomDto> response1 = controller.createCustomMushroomWithConditions(mushroomCreationDTO);

        verify(service, times(1)).createCustomMushroom(mushroomCreationDTO);
        assertEquals(HttpStatus.CREATED, response1.getStatusCode());
        assertEquals(mushroomDto, response1.getBody());
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
        user.setUsername("john");

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

        when(service.getCustom("john")).thenReturn(customDtos);
        when(service.getAllDefault()).thenReturn(defaultMushroomDtos);


        ResponseEntity<List<MushroomDto>> response = controller.getDefaultAndCustom("john");

        assertEquals(expected, response.getBody());
        assertEquals(HttpStatus.FOUND, response.getStatusCode());

        verify(service, times(1)).getCustom("john");
        verify(service, times(1)).getAllDefault();
    }

    @Test
    void testUpdateMushroomController()
    {
        IdealCondition ideal1 = new IdealCondition();
        IdealCondition ideal2 = new IdealCondition();
        IdealCondition ideal3 = new IdealCondition();

        List<IdealCondition> idealConditionList = new ArrayList<>();

        idealConditionList.add(ideal1);
        idealConditionList.add(ideal2);
        idealConditionList.add(ideal3);

        Set<IdealCondition> idealConditions = new HashSet<>();

        idealConditions.add(ideal1);
        idealConditions.add(ideal2);
        idealConditions.add(ideal3);

        MushroomDto returnDto = new MushroomDto();

        returnDto.setId(1L);
        returnDto.setName("Fugly");
        returnDto.setDescription("Bob THE builder");

        MushroomUpdateDto updateDto = new MushroomUpdateDto();
        updateDto.setId(1L);
        updateDto.setName("Fugly");
        updateDto.setDescription("Bob THE builder");
        updateDto.setIdealConditions(idealConditionList);

        when(service.updateMushroom(updateDto)).thenReturn(returnDto);

        ResponseEntity<MushroomDto> response = controller.updateMushroom(updateDto);

        assertEquals(returnDto, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(service, times(1)).updateMushroom(updateDto);
    }

    @Test
    void archiveDefaultMushroomAsAdminSuccess() {
        UserEntity admin = new UserEntity();
        admin.setId(1);
        admin.setPassword("password");
        admin.setUsername("admin");

        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        mushroom.setName("Mushroom");
        mushroom.setArchived(false);

        ResponseEntity<String> response = controller.archiveMushroom(9, "JWT_TOKEN");

        assertEquals("Archived", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
