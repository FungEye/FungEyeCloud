package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.CustomMushroomCreationDto;
import fungeye.cloud.domain.dtos.DefaultMushroomCreationDto;
import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.MushroomRepository;
import fungeye.cloud.persistence.repository.UserRepository;
import fungeye.cloud.security.JwtGenerator;
import fungeye.cloud.service.mappers.MushroomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MushroomServiceTest {

    @Mock
    private MushroomRepository repository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtGenerator generator;


    @InjectMocks
    private MushroomService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
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

        Mushroom saved = MushroomMapper.mapDefaultCreateToMushroom(defaultMushroom);
        saved.setId(1L);

        UserEntity user = new UserEntity();
        user.setId(1);
        saved.setUser(user);

        MushroomDto expected = MushroomMapper.mapToMushroomDto(saved);

        when(repository.save(any())).thenReturn(saved);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        MushroomDto actual = service.createDefaultMushroom(defaultMushroom);

        assertEquals(expected, actual);

        verify(repository, times(1)).save(any());
    }

    @Test
    void testCustomCreateMushroom() {
        CustomMushroomCreationDto customMushroom = new CustomMushroomCreationDto();
        customMushroom.setName("Mushroom");
        customMushroom.setDescription("Test mushroom");
        customMushroom.setOrigin("Denmark");
        customMushroom.setUsername("john");

        Mushroom saved = MushroomMapper.mapCustomCreateToMushroom(customMushroom);
        saved.setId(1L);

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setUsername("john");
        saved.setUser(user);

        MushroomDto expected = MushroomMapper.mapToMushroomDto(saved);

        when(repository.save(any())).thenReturn(saved);
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));

        MushroomDto actual = service.createCustomMushroom(customMushroom);

        assertEquals(expected, actual);

        verify(repository, times(1)).save(any());
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
}
