package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.persistence.repository.MushroomRepository;
import fungeye.cloud.service.mappers.MushroomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MushroomServiceTest {

    @Mock
    private MushroomRepository repository;

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

        Mushroom mushroomToSave = MushroomMapper.mapCreateToMushroom(toCreate);

        Mushroom saved = MushroomMapper.mapCreateToMushroom(toCreate);
        saved.setId(1L);

        MushroomDto expected = MushroomMapper.mapToMushroomDto(saved);

        when(repository.save(mushroomToSave)).thenReturn(saved);

        MushroomDto actual = service.createMushroom(toCreate);

        assertEquals(expected, actual);

        verify(repository, times(1)).save(mushroomToSave);
    }

    @Test
    void testGetById() {
        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        mushroom.setName("Mushroom");
        mushroom.setDescription("Test mushroom");

        MushroomDto expected = MushroomMapper.mapToMushroomDto(mushroom);

        when(repository.existsById(mushroom.getId())).thenReturn(true);
        when(repository.findById(mushroom.getId())).thenReturn(Optional.of(mushroom));

        MushroomDto actual = service.getById(mushroom.getId());

        assertEquals(expected, actual);

        verify(repository, times(1)).findById(mushroom.getId());
    }

    @Test
    void testGetByIdNotFound() {
        Long id = 1L;

        when(repository.existsById(id)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> service.getById(id));
        repository.existsById(1L);

        verify(repository, times(1)).existsById(id);
    }

    @Test
    void testGetAll() {
        Mushroom mushroom1 = new Mushroom();
        mushroom1.setId(1L);
        mushroom1.setName("Mushroom1");
        mushroom1.setDescription("Test mushroom 1");

        Mushroom mushroom2 = new Mushroom();
        mushroom2.setId(2L);
        mushroom2.setName("Mushroom2");
        mushroom2.setDescription("Test mushroom 2");

        List<Mushroom> allMushrooms = new ArrayList<>();
        allMushrooms.add(mushroom1);
        allMushrooms.add(mushroom2);

        List<MushroomDto> expected = new ArrayList<>();
        expected.add(MushroomMapper.mapToMushroomDto(mushroom1));
        expected.add(MushroomMapper.mapToMushroomDto(mushroom2));

        when(repository.findAll()).thenReturn(allMushrooms);

        List<MushroomDto> actual = service.getAll();

        assertEquals(expected, actual);

        verify(repository, times(1)).findAll();
    }
}
