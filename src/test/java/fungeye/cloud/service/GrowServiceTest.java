package fungeye.cloud.service;
import fungeye.cloud.domain.dtos.date.DateTimeDto;
import fungeye.cloud.domain.dtos.date.SimpleDateDto;
import fungeye.cloud.domain.dtos.grow.GrowCreationDto;
import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.grow.GrowIdMushroomNameDto;
import fungeye.cloud.domain.dtos.grow.GrowUpdateDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.persistence.repository.GrowRepository;
import fungeye.cloud.service.mappers.DateTimeMapper;
import fungeye.cloud.service.mappers.GrowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrowServiceTest {

    private GrowRepository repository;

    private GrowService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(GrowRepository.class);
        service = new GrowService(repository);
    }

    @Test
    void testCreateGrow() throws Exception{
        // Arrange

        GrowCreationDto dto = new GrowCreationDto();
        dto.setBoxId(1L);
        dto.setMushroomId(1L);
        dto.setUsername("john");
        dto.setDevelopStage("spawn run");

        SimpleDateDto dateDto = new SimpleDateDto(2023, 5, 5);
        dto.setDate(dateDto);

        Grow growToSave = GrowMapper.mapFromCreationDto(dto);

        Grow savedGrow = GrowMapper.mapFromCreationDto(dto);
        savedGrow.setId(1L);

        Mockito.when(repository.save(growToSave)).thenReturn(savedGrow);

        // Act
        GrowDto dtoOut = service.createGrow(dto);

        // Assert
        assertEquals(dto.getMushroomId(), dtoOut.getMushroomId());
        assertEquals(dto.getBoxId(), dtoOut.getBoxId());
        assertEquals(dto.getDevelopStage(), dtoOut.getStage());
    }

    @Test
    void testGetGrowById()throws Exception{

        Box box = new Box();
        box.setId(1L);

        DateTimeDto dateTimeDto = DateTimeMapper.mapToDateDto(LocalDate.now());

        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);

        Grow grow = new Grow();
        grow.setId(1L);
        grow.setDateStarted(LocalDate.now());
        grow.setDevelopmentStage("spawn run");
        grow.setBox(box);
        grow.setIsActive(true);

        GrowDto dto = new GrowDto();
        dto.setId(1L);
        dto.setDate(dateTimeDto);
        dto.setStage("spawn run");
        dto.setBoxId(box.getId());
        dto.setMushroomId(mushroom.getId());
        dto.setActive(true);

        Grow grow1 = GrowMapper.mapFromDto(dto);

        Mockito.when(repository.findById(grow.getId())).thenReturn(Optional.of(grow1));

        GrowDto actual = service.getGrowById(grow1.getId());

        assertEquals(dto, actual);
    }

    @Test
    void testGetAllGrowsByUsername()throws Exception{

        List<GrowDto> grows = new ArrayList<>();
        DateTimeDto dateTimeDto = DateTimeMapper.mapToDateDto(LocalDate.now());

        GrowDto dto1 = new GrowDto();
        dto1.setId(1L);
        dto1.setMushroomId(1L);
        dto1.setDate(dateTimeDto);

        GrowDto dto2 = new GrowDto();
        dto2.setId(2L);
        dto2.setMushroomId(2L);
        dto2.setDate(dateTimeDto);

        grows.add(dto1);
        grows.add(dto2);

        Mushroom mushroom1 = new Mushroom();
        mushroom1.setId(1L);

        Mushroom mushroom2 = new Mushroom();
        mushroom2.setId(2L);

        List<GrowIdMushroomNameDto> expectedIds = new ArrayList<>();

        GrowIdMushroomNameDto id1 = new GrowIdMushroomNameDto();
        id1.setId(dto1.getId());
        id1.setMushroomId(mushroom1.getId());

        GrowIdMushroomNameDto id2 = new GrowIdMushroomNameDto();
        id2.setId(dto2.getId());
        id2.setMushroomId(mushroom2.getId());

        expectedIds.add(id1);
        expectedIds.add(id2);

        List<Grow> convertedGrows = GrowMapper.mapFromGrowDtoList(grows);

        Mockito.when(repository.findGrowsByBox_UserEntity_Username("john")).thenReturn(convertedGrows);

        List<GrowIdMushroomNameDto> actual = service.getAllGrowsByUsername("john");

        assertEquals(expectedIds.size(), actual.size());

        for (int i = 0; i < expectedIds.size(); i++) {
            assertEquals(expectedIds.get(i), actual.get(i));
        }
    }

    @Test
    void testUpdateGrow()throws Exception{
        Grow initial = new Grow();
        initial.setId(1L);
        initial.setIsActive(true);
        initial.setDevelopmentStage("Fruiting");
        initial.setDateStarted(LocalDate.of(2023, 5, 5));

        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);
        initial.setMushroom(mushroom);

        Box box = new Box();
        box.setId(1L);
        initial.setBox(box);

        GrowUpdateDto update = new GrowUpdateDto();
        update.setId(1L);
        update.setIsActive(false);
        update.setDevelopStage("fruiting");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(initial));

        GrowDto returned = service.updateGrow(update);

        assertEquals(update.getId(), returned.getId());
        assertEquals(update.getIsActive(), returned.isActive());
        assertEquals(update.getDevelopStage(), returned.getStage());
    }

    @Test
    void testEndGrow()throws Exception {

        Box box = new Box();
        box.setId(1L);

        Mushroom mushroom = new Mushroom();
        mushroom.setId(1L);

        Grow grow = new Grow();
        grow.setId(1L);
        grow.setIsActive(true);
        grow.setDevelopmentStage("fruiting");
        grow.setDateStarted(LocalDate.of(2023, 5, 5));
        grow.setBox(box);
        grow.setMushroom(mushroom);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(grow));

        GrowDto dto = service.endGrow(1L);
        dto.setActive(false);

        assertEquals(grow.getId(), dto.getId());
        assertEquals(grow.getIsActive(), dto.isActive());
    }

}
