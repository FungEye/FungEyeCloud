package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.ideal.IdealConditionDto;
import fungeye.cloud.domain.dtos.mushroom.*;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.service.MushroomService;
import fungeye.cloud.service.mappers.IdealConditionsMapper;
import fungeye.cloud.service.mappers.MushroomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

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

    @Deprecated(forRemoval = true)
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

        MushroomWithConditionsDto mushroomDto = new MushroomWithConditionsDto();
        mushroomDto.setId(1L);
        mushroomDto.setName("Portobello");
        mushroomDto.setDescription("Large mushroom with a meaty texture.");

        List<IdealConditionDto> idealConditionDtos = new ArrayList<>();

        IdealConditionDto conditionDto1 = new IdealConditionDto();
        conditionDto1.setMushroomId(1L);
        conditionDto1.setDevelopmentStage("spawn run");
        conditionDto1.setTempHigh(27.0);
        conditionDto1.setTempLow(24.0);
        conditionDto1.setHumidityHigh(80.0);
        conditionDto1.setHumidityLow(70.0);
        conditionDto1.setCo2High(800.0);
        conditionDto1.setCo2Low(200.0);
        conditionDto1.setLightHigh(1000.0);
        conditionDto1.setLightLow(200.0);

        IdealConditionDto conditionDto2 = new IdealConditionDto();
        conditionDto2.setMushroomId(1L);
        conditionDto2.setDevelopmentStage("pinning");
        conditionDto2.setTempHigh(27.0);
        conditionDto2.setTempLow(24.0);
        conditionDto2.setHumidityHigh(80.0);
        conditionDto2.setHumidityLow(70.0);
        conditionDto2.setCo2High(800.0);
        conditionDto2.setCo2Low(200.0);
        conditionDto2.setLightHigh(1000.0);
        conditionDto2.setLightLow(200.0);

        IdealConditionDto conditionDto3 = new IdealConditionDto();
        conditionDto3.setMushroomId(1L);
        conditionDto3.setDevelopmentStage("fruiting");
        conditionDto3.setTempHigh(27.0);
        conditionDto3.setTempLow(24.0);
        conditionDto3.setHumidityHigh(80.0);
        conditionDto3.setHumidityLow(70.0);
        conditionDto3.setCo2High(800.0);
        conditionDto3.setCo2Low(200.0);
        conditionDto3.setLightHigh(1000.0);
        conditionDto3.setLightLow(200.0);

        idealConditionDtos.add(conditionDto1);
        idealConditionDtos.add(conditionDto2);
        idealConditionDtos.add(conditionDto3);

        mushroomDto.setIdealConditionDtos(idealConditionDtos);

        Mushroom saved = MushroomMapper.mapFromMushroomWithConditionsDto(mushroomDto);
        saved.setId(1L);

        UserEntity user = new UserEntity();
        user.setId(1);
        saved.setUser(user);

        Set<IdealCondition> idealConditionSet = new HashSet<>();
        for (IdealConditionDto dto:
                idealConditionDtos) {
            idealConditionSet.add(IdealConditionsMapper.mapToIdealCondition(dto));
        }
        saved.setIdealConditions(idealConditionSet);

        when(service.createDefaultMushroom(mushroomCreationDTO)).thenReturn(mushroomDto);

        ResponseEntity<MushroomWithConditionsDto> response1 = controller.createDefaultMushroom(mushroomCreationDTO);

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

        MushroomWithConditionsDto mushroomDto = new MushroomWithConditionsDto();
        mushroomDto.setId(1L);
        mushroomDto.setName("Portobello");
        mushroomDto.setDescription("Large mushroom with a meaty texture.");

        UserEntity user = new UserEntity();
        user.setId(2);
        user.setUsername("john");

        List<IdealConditionDto> idealConditionDtos = new ArrayList<>();

        IdealConditionDto conditionDto1 = new IdealConditionDto();
        conditionDto1.setMushroomId(1L);
        conditionDto1.setDevelopmentStage("spawn run");
        conditionDto1.setTempHigh(27.0);
        conditionDto1.setTempLow(24.0);
        conditionDto1.setHumidityHigh(80.0);
        conditionDto1.setHumidityLow(70.0);
        conditionDto1.setCo2High(800.0);
        conditionDto1.setCo2Low(200.0);
        conditionDto1.setLightHigh(1000.0);
        conditionDto1.setLightLow(200.0);

        IdealConditionDto conditionDto2 = new IdealConditionDto();
        conditionDto2.setMushroomId(1L);
        conditionDto2.setDevelopmentStage("pinning");
        conditionDto2.setTempHigh(27.0);
        conditionDto2.setTempLow(24.0);
        conditionDto2.setHumidityHigh(80.0);
        conditionDto2.setHumidityLow(70.0);
        conditionDto2.setCo2High(800.0);
        conditionDto2.setCo2Low(200.0);
        conditionDto2.setLightHigh(1000.0);
        conditionDto2.setLightLow(200.0);

        IdealConditionDto conditionDto3 = new IdealConditionDto();
        conditionDto3.setMushroomId(1L);
        conditionDto3.setDevelopmentStage("fruiting");
        conditionDto3.setTempHigh(27.0);
        conditionDto3.setTempLow(24.0);
        conditionDto3.setHumidityHigh(80.0);
        conditionDto3.setHumidityLow(70.0);
        conditionDto3.setCo2High(800.0);
        conditionDto3.setCo2Low(200.0);
        conditionDto3.setLightHigh(1000.0);
        conditionDto3.setLightLow(200.0);

        idealConditionDtos.add(conditionDto1);
        idealConditionDtos.add(conditionDto2);
        idealConditionDtos.add(conditionDto3);

        mushroomDto.setIdealConditionDtos(idealConditionDtos);

        Mushroom saved = MushroomMapper.mapFromMushroomWithConditionsDto(mushroomDto);
        saved.setId(1L);

        saved.setUser(user);

        Set<IdealCondition> idealConditionSet = new HashSet<>();
        for (IdealConditionDto dto:
                idealConditionDtos) {
            idealConditionSet.add(IdealConditionsMapper.mapToIdealCondition(dto));
        }
        saved.setIdealConditions(idealConditionSet);

        when(service.createCustomMushroom(mushroomCreationDTO)).thenReturn(mushroomDto);

        ResponseEntity<MushroomWithConditionsDto> response1 = controller.createCustomMushroomWithConditions(mushroomCreationDTO);

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
        assertEquals(HttpStatus.OK, response.getStatusCode());
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
        assertEquals(HttpStatus.OK, response.getStatusCode());
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
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(service, times(1)).getCustom("john");
        verify(service, times(1)).getAllDefault();
    }

    @Test
    void testUpdateMushroomController()
    {
        IdealConditionDto ideal1 = new IdealConditionDto();
        IdealConditionDto ideal2 = new IdealConditionDto();
        IdealConditionDto ideal3 = new IdealConditionDto();

        List<IdealConditionDto> idealConditionList = new ArrayList<>();

        idealConditionList.add(ideal1);
        idealConditionList.add(ideal2);
        idealConditionList.add(ideal3);

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

    @Test
    void testGetDefaultAndCustomWithConditions() {
        UserEntity admin = new UserEntity();
        admin.setId(2);
        admin.setUsername("john");

        Mushroom mushroom3 = new Mushroom();
        mushroom3.setId(3L);
        mushroom3.setName("Mushroom3");
        mushroom3.setDescription("Default mushroom 1");
        mushroom3.setUser(admin);

        Set<IdealCondition> ideal = new HashSet<>();

        IdealCondition idealCondition1 = new IdealCondition();

        idealCondition1.setId(new IdealConditionId(mushroom3.getId(), "spawn run"));
        idealCondition1.setMushroom(mushroom3);
        idealCondition1.setTemperatureLow(20.0);
        idealCondition1.setTemperatureHigh(25.0);
        idealCondition1.setHumidityLow(60.0);
        idealCondition1.setHumidityHigh(80.0);
        idealCondition1.setCo2Low(100.0);
        idealCondition1.setCo2High(1000.0);
        idealCondition1.setLightLow(100.0);
        idealCondition1.setLightHigh(1000.0);
        ideal.add(idealCondition1);

        List<IdealCondition> conditionList = ideal.stream().toList();

        mushroom3.setIdealConditions(ideal);

        List<MushroomWithConditionsDto> mushroomWithConditionsDtos = new ArrayList<>();
        MushroomWithConditionsDto dto = MushroomMapper.mapToMushroomWithConditionsDto(mushroom3);
        mushroomWithConditionsDtos.add(dto);

        when(service.getCustomMushroomsWithConditions("john")).thenReturn(mushroomWithConditionsDtos);

        ResponseEntity<List<MushroomWithConditionsDto>> response = controller.getDefaultAndCustomWithConditions("john");

        assertEquals(mushroom3.getId(), Objects.requireNonNull(response.getBody()).get(0).getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
