package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.persistence.repository.GrowRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BoxServiceTest {

    @Mock
    private BoxRepository repository;

    @Mock
    private GrowRepository growRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBox() {
        // Given
        Box box = new Box();
        when(repository.save(ArgumentMatchers.any())).thenReturn(box);

        // When
        BoxService service = new BoxService(repository, growRepository);
        BoxDto dto = service.createBox();

        // Then
        Assertions.assertNotNull(dto);
        verify(repository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void testGetById() {
        // Given
        Box box = new Box();
        box.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(box));

        // When
        BoxService service = new BoxService(repository, growRepository);
        BoxDetailsDto dto = service.getById(1L);

        // Then
        Assertions.assertNotNull(dto);
        assertEquals(box.getId(), dto.getId());
        verify(repository, Mockito.times(1)).findById(ArgumentMatchers.any());
    }

    @Test
    void testGetAll() {
        Box box = new Box();
        box.setId(1L);

        List<Box> boxes = new ArrayList<>();
        boxes.add(box);

        BoxDetailsDto dto = new BoxDetailsDto();
        dto.setId(1L);

        List<BoxDetailsDto> dtos = new ArrayList<>();
        dtos.add(dto);

        when(repository.findAll()).thenReturn(boxes);

        BoxService service = new BoxService(repository, growRepository);
        List<BoxDetailsDto> result = service.getAll();

        assertEquals(dtos.get(0).getId(), result.get(0).getId());
        verify(repository, times(1)).findAll();

    }

    @Test
    void testGetAllBoxesByUsername() throws Exception
    {
        // todo
    }
}
