package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.*;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.*;
import fungeye.cloud.service.mappers.BoxMapper;
import fungeye.cloud.service.mappers.GrowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BoxServiceTest {

    @Mock
    private BoxRepository repository;

    @Mock
    private GrowRepository growRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MeasuredConditionRepository measuredConditionRepository;

    private BoxService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new BoxService(repository, growRepository, userRepository, measuredConditionRepository);
    }

    @Test
    void testCreateBox() {
        // Given
        BoxCreationDto dto = new BoxCreationDto();
        dto.setUsername("john");
        dto.setEui("0123456789ABCDEF");

        Box box = new Box();

        UserEntity user = new UserEntity();
        user.setId(1);
        user.setUsername("john");
        box.setUserEntity(user);

        box.setEui("0123456789ABCDEF");
        box.setId(1L);

        BoxDto expected = BoxMapper.mapToSimpleDto(box);

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));
        when(repository.save(ArgumentMatchers.any())).thenReturn(box);

        // When
        BoxDto actual = service.createBox(dto);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void testGetById() {
        // Given
        Box box = new Box();
        box.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(box));

        // When
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

        List<BoxDetailsDto> result = service.getAll();

        assertEquals(dtos.get(0).getId(), result.get(0).getId());
        verify(repository, times(1)).findAll();

    }

    @Test
    void testGetAllEmptyByUsername()
    {
        String username = "Liepa";

        Box box1 = new Box();
        Box box2 = new Box();
        Box box3 = new Box();
        box1.setId(1L);
        box2.setId(2L);
        box3.setId(3L);

        List<Box> boxes = new ArrayList<>();

        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);

        BoxDto dto1 = new BoxDto();
        BoxDto dto2 = new BoxDto();
        BoxDto dto3 = new BoxDto();
        dto1.setId(1L);
        dto2.setId(2L);
        dto3.setId(3L);

        List<BoxDto> dtos = new ArrayList<>();

        dtos.add(dto1);
        dtos.add(dto2);
        dtos.add(dto3);

        when(repository.findBoxesByUserEntity_Username(username)).thenReturn(boxes);

        List<BoxDto> result = service.getAllEmptyByUserName(username);

        assertEquals(dtos.get(0).getId(), result.get(0).getId());
        verify(repository, times(1)).findBoxesByUserEntity_Username(username);
    }

    @Test
    void testGetAllByUsername() {
        String username = "john";

        Mushroom mushroom = new Mushroom();
        mushroom.setName("Portobello");
        mushroom.setId(2L);

        Set<Grow> grows = new HashSet<>();

        Grow grow = new Grow();
        grow.setId(1L);
        grow.setMushroom(mushroom);

        grows.add(grow);

        Box box = new Box();
        box.setId(1L);
        box.setGrows(grows);

        List<Box> boxes = new ArrayList<>();
        boxes.add(box);

        SimpleBoxGrowDto expected1 = new SimpleBoxGrowDto();
        expected1.setId(1L);

        List<GrowIdMushroomNameDto> mushroomNameDtos = GrowMapper.mapToGrowIdMushroomNameDtoList(grows);
        expected1.setSimpleGrowDtos(mushroomNameDtos);

        List<SimpleBoxGrowDto> expected = new ArrayList<>();
        expected.add(expected1);

        when(repository.findByUserEntity_UsernameOrderByIdAsc(username)).thenReturn(boxes);
        when(growRepository.findByBox_Id(any())).thenReturn(grows.stream().toList());

        List<SimpleBoxGrowDto> actual = service.getAllByUsername(username);

        assertEquals(expected, actual);
    }
}
