package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.persistence.repository.BoxRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class BoxServiceTest {

    @Mock
    private BoxRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBox() {
        // Given
        Box box = new Box();
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(box);

        // When
        BoxService service = new BoxService(repository);
        BoxDto dto = service.createBox();

        // Then
        Assertions.assertNotNull(dto);
        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any());
    }

    @Test
    void testGetById() {
        // Given
        Box box = new Box();
        box.setId(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(box));

        // When
        BoxService service = new BoxService(repository);
        BoxDetailsDto dto = service.getById(1L);

        // Then
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(box.getId(), dto.getId());
        Mockito.verify(repository, Mockito.times(1)).findById(ArgumentMatchers.any());
    }
}
