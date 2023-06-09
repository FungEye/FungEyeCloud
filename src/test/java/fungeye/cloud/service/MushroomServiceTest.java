package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.ideal.IdealConditionCreationDto;
import fungeye.cloud.domain.dtos.ideal.IdealConditionDto;
import fungeye.cloud.domain.dtos.mushroom.*;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.IdealConditionRepository;
import fungeye.cloud.persistence.repository.MushroomRepository;
import fungeye.cloud.persistence.repository.UserRepository;
import fungeye.cloud.security.JwtGenerator;
import fungeye.cloud.service.mappers.IdealConditionsMapper;
import fungeye.cloud.service.mappers.MushroomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MushroomServiceTest {
    @Mock
    private MushroomRepository repository;

    @Mock
    private IdealConditionRepository idealConditionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtGenerator generator;


    @InjectMocks
    private MushroomService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateMushroom() {
        MushroomCreationDTO toCreate = new MushroomCreationDTO();
        toCreate.setName("Mushroom");
        toCreate.setDescription("Test mushroom");
        toCreate.setOrigin("Denmark");
        toCreate.setUserId(1);

        Mushroom saved = MushroomMapper.mapCreateToMushroom(toCreate);
        saved.setId(1L);

        UserEntity user = new UserEntity();
        user.setId(1);
        saved.setUser(user);

        MushroomDto expected = MushroomMapper.mapToMushroomDto(saved);

        when(repository.save(any())).thenReturn(saved);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        MushroomDto actual = service.createMushroom(toCreate);

        assertEquals(expected, actual);

        verify(repository, times(1)).save(any());
    }

    @Test
    void testDefaultCreateMushroom() {
        DefaultMushroomCreationDto defaultMushroom = new DefaultMushroomCreationDto();
        defaultMushroom.setName("Mushroom");
        defaultMushroom.setDescription("Test mushroom");
        defaultMushroom.setOrigin("Denmark");

        List<IdealConditionCreationDto> idealConditionDtos = new ArrayList<>();

        IdealConditionCreationDto creationDto1 = new IdealConditionCreationDto();
        creationDto1.setDevelopmentStage("spawn run");
        creationDto1.setTempHigh(27.0);
        creationDto1.setTempLow(24.0);
        creationDto1.setHumidityHigh(80.0);
        creationDto1.setHumidityLow(70.0);
        creationDto1.setCo2High(800.0);
        creationDto1.setCo2Low(200.0);
        creationDto1.setLightHigh(1000.0);
        creationDto1.setLightLow(200.0);

        IdealConditionCreationDto creationDto2 = new IdealConditionCreationDto();
        creationDto2.setDevelopmentStage("pinning");
        creationDto2.setTempHigh(27.0);
        creationDto2.setTempLow(24.0);
        creationDto2.setHumidityHigh(80.0);
        creationDto2.setHumidityLow(70.0);
        creationDto2.setCo2High(800.0);
        creationDto2.setCo2Low(200.0);
        creationDto2.setLightHigh(1000.0);
        creationDto2.setLightLow(200.0);

        IdealConditionCreationDto creationDto3 = new IdealConditionCreationDto();
        creationDto3.setDevelopmentStage("fruiting");
        creationDto3.setTempHigh(27.0);
        creationDto3.setTempLow(24.0);
        creationDto3.setHumidityHigh(80.0);
        creationDto3.setHumidityLow(70.0);
        creationDto3.setCo2High(800.0);
        creationDto3.setCo2Low(200.0);
        creationDto3.setLightHigh(1000.0);
        creationDto3.setLightLow(200.0);

        idealConditionDtos.add(creationDto1);
        idealConditionDtos.add(creationDto2);
        idealConditionDtos.add(creationDto3);

        defaultMushroom.setIdealConditionCreationDtos(idealConditionDtos);

        Mushroom saved = MushroomMapper.mapDefaultCreateToMushroom(defaultMushroom);
        saved.setId(1L);

        UserEntity user = new UserEntity();
        user.setId(3);
        saved.setUser(user);

        Set<IdealCondition> idealConditionSet = new HashSet<>();
        for (IdealConditionCreationDto creationDto :
                idealConditionDtos) {
            idealConditionSet.add(IdealConditionsMapper.mapCreateToIdealCondition(creationDto));
        }
        saved.setIdealConditions(idealConditionSet);

        List<IdealConditionDto> idealConditionDtoListExpected = new ArrayList<>();
        // Doing the same stage thrice to avoid ordering issues with stream
        IdealCondition firstStage = IdealConditionsMapper.mapCreateToIdealCondition(creationDto1);
        IdealConditionDto firstStageDto = IdealConditionsMapper.mapToIdealConditionDto(firstStage);
        idealConditionDtoListExpected.add(firstStageDto);
        idealConditionDtoListExpected.add(firstStageDto);
        idealConditionDtoListExpected.add(firstStageDto);

        MushroomWithConditionsDto expected = MushroomMapper.mapToMushroomWithConditionsDto(saved);

        expected.setIdealConditionDtos(idealConditionDtoListExpected);

        when(repository.save(any())).thenReturn(saved);
        when(userRepository.findById(3)).thenReturn(Optional.of(user));
        when(idealConditionRepository.save(any())).thenReturn(IdealConditionsMapper.mapCreateToIdealCondition(creationDto1));

        MushroomWithConditionsDto actual = service.createDefaultMushroom(defaultMushroom);

        assertEquals(expected, actual);
        verify(repository, times(1)).save(any());
        verify(userRepository, times(1)).findById(3);
        verify(idealConditionRepository, times(3)).save(any());
    }

    @Test
    void testCustomCreateMushroom() {
        CustomMushroomCreationDto customMushroom = new CustomMushroomCreationDto();
        customMushroom.setName("Mushroom");
        customMushroom.setDescription("Test mushroom");
        customMushroom.setOrigin("Denmark");
        customMushroom.setUsername("john");

        List<IdealConditionCreationDto> idealConditionDtos = new ArrayList<>();

        IdealConditionCreationDto creationDto1 = new IdealConditionCreationDto();
        creationDto1.setDevelopmentStage("spawn run");
        creationDto1.setTempHigh(27.0);
        creationDto1.setTempLow(24.0);
        creationDto1.setHumidityHigh(80.0);
        creationDto1.setHumidityLow(70.0);
        creationDto1.setCo2High(800.0);
        creationDto1.setCo2Low(200.0);
        creationDto1.setLightHigh(1000.0);
        creationDto1.setLightLow(200.0);

        IdealConditionCreationDto creationDto2 = new IdealConditionCreationDto();
        creationDto2.setDevelopmentStage("pinning");
        creationDto2.setTempHigh(27.0);
        creationDto2.setTempLow(24.0);
        creationDto2.setHumidityHigh(80.0);
        creationDto2.setHumidityLow(70.0);
        creationDto2.setCo2High(800.0);
        creationDto2.setCo2Low(200.0);
        creationDto2.setLightHigh(1000.0);
        creationDto2.setLightLow(200.0);

        IdealConditionCreationDto creationDto3 = new IdealConditionCreationDto();
        creationDto3.setDevelopmentStage("fruiting");
        creationDto3.setTempHigh(27.0);
        creationDto3.setTempLow(24.0);
        creationDto3.setHumidityHigh(80.0);
        creationDto3.setHumidityLow(70.0);
        creationDto3.setCo2High(800.0);
        creationDto3.setCo2Low(200.0);
        creationDto3.setLightHigh(1000.0);
        creationDto3.setLightLow(200.0);

        idealConditionDtos.add(creationDto1);
        idealConditionDtos.add(creationDto2);
        idealConditionDtos.add(creationDto3);

        customMushroom.setIdealConditionCreationDtos(idealConditionDtos);

        Mushroom saved = MushroomMapper.mapCustomCreateToMushroom(customMushroom);
        saved.setId(1L);

        UserEntity user = new UserEntity();
        user.setId(1);
        saved.setUser(user);

        Set<IdealCondition> idealConditionSet = new HashSet<>();
        for (IdealConditionCreationDto creationDto :
                idealConditionDtos) {
            idealConditionSet.add(IdealConditionsMapper.mapCreateToIdealCondition(creationDto));
        }
        saved.setIdealConditions(idealConditionSet);

        List<IdealConditionDto> idealConditionDtoListExpected = new ArrayList<>();
        // Doing the same stage thrice to avoid ordering issues with stream
        IdealCondition firstStage = IdealConditionsMapper.mapCreateToIdealCondition(creationDto1);
        IdealConditionDto firstStageDto = IdealConditionsMapper.mapToIdealConditionDto(firstStage);
        idealConditionDtoListExpected.add(firstStageDto);
        idealConditionDtoListExpected.add(firstStageDto);
        idealConditionDtoListExpected.add(firstStageDto);

        MushroomWithConditionsDto expected = MushroomMapper.mapToMushroomWithConditionsDto(saved);

        expected.setIdealConditionDtos(idealConditionDtoListExpected);

        when(repository.save(any())).thenReturn(saved);
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));
        when(idealConditionRepository.save(any())).thenReturn(IdealConditionsMapper.mapCreateToIdealCondition(creationDto1));

        MushroomWithConditionsDto actual = service.createCustomMushroom(customMushroom);

        assertEquals(expected, actual);
        verify(repository, times(1)).save(any());
        verify(userRepository, times(1)).findByUsername("john");
        verify(idealConditionRepository, times(3)).save(any());
    }

    @Test
    void testGetById() {
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        mushroom.setName("Mushroom");
        mushroom.setDescription("Test mushroom");
        UserEntity user = new UserEntity();
        user.setId(1);
        mushroom.setUser(user);

        MushroomDto expected = MushroomMapper.mapToMushroomDto(mushroom);

        when(repository.existsById(mushroom.getId())).thenReturn(true);
        when(repository.findById(mushroom.getId())).thenReturn(Optional.of(mushroom));

        MushroomDto actual = service.getByMushroomId(mushroom.getId());

        assertEquals(expected, actual);

        verify(repository, times(1)).findById(mushroom.getId());
    }

    @Test
    void testGetByIdNotFound() {
        Long id = 1L;

        when(repository.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> service.getByMushroomId(id));
        repository.existsById(1L);

        verify(repository, times(1)).existsById(id);
    }

    @Test
    void testGetAllDefault() {
        UserEntity user = new UserEntity();
        user.setId(3);

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

        List<Mushroom> allMushrooms = new ArrayList<>();
        allMushrooms.add(mushroom1);
        allMushrooms.add(mushroom2);

        List<MushroomDto> expected = new ArrayList<>();
        expected.add(MushroomMapper.mapToMushroomDto(mushroom1));
        expected.add(MushroomMapper.mapToMushroomDto(mushroom2));

        when(repository.findByUser_Id(3)).thenReturn(allMushrooms);

        List<MushroomDto> actual = service.getAllDefault();

        assertEquals(expected, actual);

        verify(repository, times(1)).findByUser_Id(3);
    }

    @Test
    void testGetCustom() {
        UserEntity admin = new UserEntity();
        admin.setId(3);
        admin.setUsername("admin");

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

        List<Mushroom> customMushrooms = new ArrayList<>();
        customMushrooms.add(mushroom1);
        customMushrooms.add(mushroom2);

        List<Mushroom> defaultMushrooms = new ArrayList<>();
        defaultMushrooms.add(mushroom3);

        List<MushroomDto> expected = new ArrayList<>();
        expected.add(MushroomMapper.mapToMushroomDto(mushroom1));
        expected.add(MushroomMapper.mapToMushroomDto(mushroom2));

        when(repository.findByUser_Username("john")).thenReturn(customMushrooms);

        List<MushroomDto> actual = service.getCustom("john");

        assertEquals(expected, actual);

        verify(repository, times(1)).findByUser_Username("john");
    }

    @Test
    void testUpdateMushroom() {
        MushroomUpdateDto argument = new MushroomUpdateDto();
        argument.setId(1L);
        argument.setName("Updated name");
        argument.setDescription("Updated Description");
        argument.setOrigin("Updated origin");
        argument.setImageUrl("Updated image");

        MushroomDto expected = new MushroomDto();
        expected.setId(1L);
        expected.setName(argument.getName());
        expected.setDescription(argument.getDescription());
        expected.setOrigin(argument.getOrigin());
        expected.setImageUrl(argument.getImageUrl());

        IdealConditionDto icd1 = new IdealConditionDto();
        icd1.setMushroomId(1L);
        icd1.setDevelopmentStage("pinning");
        icd1.setTempLow(1);
        icd1.setTempHigh(10);
        icd1.setHumidityLow(1);
        icd1.setHumidityHigh(10);
        icd1.setLightLow(1);
        icd1.setLightHigh(10);
        icd1.setCo2Low(1);
        icd1.setCo2High(10);

        IdealConditionDto icd2 = new IdealConditionDto();
        icd2.setMushroomId(1L);
        icd2.setDevelopmentStage("spawn run");
        icd2.setTempLow(1);
        icd2.setTempHigh(10);
        icd2.setHumidityLow(1);
        icd2.setHumidityHigh(10);
        icd2.setLightLow(1);
        icd2.setLightHigh(10);
        icd2.setCo2Low(1);
        icd2.setCo2High(10);

        IdealConditionDto icd3 = new IdealConditionDto();
        icd3.setMushroomId(1L);
        icd3.setDevelopmentStage("fruiting");
        icd3.setTempLow(1);
        icd3.setTempHigh(10);
        icd3.setHumidityLow(1);
        icd3.setHumidityHigh(10);
        icd3.setLightLow(1);
        icd3.setLightHigh(10);
        icd3.setCo2Low(1);
        icd3.setCo2High(10);

        List<IdealConditionDto> argumentConditions = new ArrayList<>();
        argumentConditions.add(icd1);
        argumentConditions.add(icd2);
        argumentConditions.add(icd3);

        argument.setIdealConditions(argumentConditions);

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setUsername("Bob");

        Mushroom fromDb = new Mushroom();
        fromDb.setId(1L);
        fromDb.setName("Old name");
        fromDb.setOrigin("Old origin");
        fromDb.setDescription("Old desc");
        fromDb.setImageUrl("Old url");
        fromDb.setUser(user);

        Mushroom dbResult = new Mushroom();
        dbResult.setId(1L);
        dbResult.setName(argument.getName());
        dbResult.setOrigin(argument.getOrigin());
        dbResult.setDescription(argument.getDescription());
        dbResult.setImageUrl(argument.getImageUrl());
        dbResult.setUser(user);


        when(repository.findById(1L)).thenReturn(Optional.of(fromDb));
        when(idealConditionRepository.findByMushroom_Id(1L)).thenReturn(new ArrayList<>());
        when(idealConditionRepository.save(any())).thenReturn(new IdealCondition());
        when(repository.save(fromDb)).thenReturn(dbResult);

        MushroomDto result = service.updateMushroom(argument);

        assertEquals(expected, result);

        verify(repository, times(1)).findById(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    void testArchiveMushroom_WithValidInputsAndNotArchived() {
        // Arrange
        long mushroomId = 1L;
        String token = "valid_token";
        Mushroom mushroom = new Mushroom();
        mushroom.setArchived(false);
        UserEntity user = new UserEntity();
        user.setUsername("username");
        mushroom.setUser(user);

        when(repository.findById(mushroomId)).thenReturn(Optional.of(mushroom));
        when(generator.getUsernameFromJwt(token.substring(7))).thenReturn("username");

        // Mock the void method to do nothing
        doNothing().when(repository).updateArchivedById(true, mushroomId);

        // Act
        service.archiveMushroom(mushroomId, token);

        // Assert
        verify(repository, times(1)).updateArchivedById(true, mushroomId);
    }

    @Test
    void testArchiveAlreadyArchivedMushroom() {
        long mushroomId = 1L;
        String token = "valid_token";

        Mushroom mushroom = new Mushroom();
        mushroom.setArchived(true);

        UserEntity user = new UserEntity();
        user.setUsername("username");
        mushroom.setUser(user);

        when(repository.findById(mushroomId)).thenReturn(Optional.of(mushroom));
        when(generator.getUsernameFromJwt(token.substring(7))).thenReturn("username");

        assertThrows(IllegalArgumentException.class, () -> service.archiveMushroom(mushroomId, token));

        assertTrue(mushroom.getArchived());
        verify(repository, never()).updateArchivedById(anyBoolean(), anyLong());
    }

    @Test
    void testArchiveMushroomWithInvalidToken() {
        long mushroomId = 1L;
        String invalidToken = "invalid_token";

        Mushroom mushroom = new Mushroom();
        mushroom.setArchived(false);

        UserEntity user = new UserEntity();
        user.setUsername("username");
        mushroom.setUser(user); // Initialize the user object

        when(repository.findById(mushroomId)).thenReturn(Optional.of(mushroom));

        assertThrows(BadCredentialsException.class, () -> service.archiveMushroom(mushroomId, invalidToken));

        assertFalse(mushroom.getArchived());
        verify(repository, never()).updateArchivedById(anyBoolean(), anyLong());
    }

    @Test
    void testGetDefaultWithConditions() {
        UserEntity admin = new UserEntity();
        admin.setId(3);
        admin.setUsername("admin");

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

        List<Mushroom> shrooms = new ArrayList<>();
        shrooms.add(mushroom3);

        when(repository.findByUser_Username("admin")).thenReturn(shrooms);
        when(idealConditionRepository.findByMushroom_Id(3L)).thenReturn(conditionList);

        List<MushroomWithConditionsDto> actual = service.getDefaultMushroomsWithConditions();

        assertEquals(actual.get(0).getId(), mushroom3.getId());
        assertEquals(actual.get(0).getIdealConditionDtos().get(0).getHumidityHigh(), 80.0);
    }

    @Test
    void testGetCustomWithConditions() {
        UserEntity admin = new UserEntity();
        admin.setId(3);
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

        List<Mushroom> shrooms = new ArrayList<>();
        shrooms.add(mushroom3);

        when(repository.findByUser_Username("john")).thenReturn(shrooms);
        when(idealConditionRepository.findByMushroom_Id(3L)).thenReturn(conditionList);

        List<MushroomWithConditionsDto> actual = service.getCustomMushroomsWithConditions("john");

        assertEquals(actual.get(0).getId(), mushroom3.getId());
        assertEquals(actual.get(0).getIdealConditionDtos().get(0).getHumidityHigh(), 80.0);
    }
}
