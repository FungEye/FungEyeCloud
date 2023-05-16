package fungeye.cloud.service;
import fungeye.cloud.domain.dtos.*;
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

public class GrowServiceTest {

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
        dto.setDevelopStage("Spawn run");

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
    void testGetAllGrowsByUsername()throws Exception{
        List<GrowDto> grows = new ArrayList<>();

        GrowDto dto1 = new GrowDto();
        dto1.setBoxId(1L);
        dto1.setMushroomId(1L);
        dto1.setStage("Spawn run");
        dto1.setActive(true);

        DateTimeDto dateTimeDto = DateTimeMapper.mapToDateDto(LocalDate.now());
        dto1.setDate(dateTimeDto);

        GrowDto dto2 = new GrowDto();
        dto2.setBoxId(2L);
        dto2.setMushroomId(2L);
        dto2.setStage("Spawn run");
        dto2.setActive(true);

        dto2.setDate(dateTimeDto);

        grows.add(dto1);
        grows.add(dto2);

        List<GrowIdDto> expectedIds = new ArrayList<>();
        GrowIdDto id1 = new GrowIdDto();
        id1.setId(dto1.getId());
        GrowIdDto id2 = new GrowIdDto();
        id2.setId(dto2.getId());
        expectedIds.add(id1);
        expectedIds.add(id2);

        List<Grow> convertedGrows = GrowMapper.mapFromGrowDtoList(grows);

        Mockito.when(repository.findGrowsByUserEntity_Username("john")).thenReturn(convertedGrows);

        List<GrowIdDto> actual = service.getAllGrowsByUsername("john");

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
        update.setDevelopStage("Fruiting");

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(initial));

        GrowDto returned = service.updateGrow(update);

        assertEquals(update.getId(), returned.getId());
        assertEquals(update.getIsActive(), returned.isActive());
        assertEquals(update.getDevelopStage(), returned.getStage());
    }
}
