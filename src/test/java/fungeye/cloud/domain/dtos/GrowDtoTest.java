package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GrowDtoTest {

    @Test
    public void testCreateGrowDto() {
        // Arrange
        Long id = 1L;
        DateTimeDto date = new DateTimeDto(2023, 4, 14, 10, 30, 0);
        String stage = "Growing";
        boolean active = true;
        Long boxId = 2L;
        List<MushroomDto> mushroomDtoList = new ArrayList<>();

        // Act
        GrowDto growDto = new GrowDto(id, date, stage, active, boxId, mushroomDtoList);

        // Assert
        assertNotNull(growDto);
        assertEquals(id, growDto.getId());
        assertEquals(date, growDto.getDate());
        assertEquals(stage, growDto.getStage());
        assertEquals(active, growDto.isActive());
        assertEquals(boxId, growDto.getBoxId());
        assertEquals(mushroomDtoList, growDto.getMushroomDtoList());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Long id = 1L;
        DateTimeDto date = new DateTimeDto(2023, 4, 14, 10, 30, 0);
        String stage = "Growing";
        boolean active = true;
        Long boxId = 2L;
        List<MushroomDto> mushroomDtoList = new ArrayList<>();

        GrowDto growDto = new GrowDto();

        // Act
        growDto.setId(id);
        growDto.setDate(date);
        growDto.setStage(stage);
        growDto.setActive(active);
        growDto.setBoxId(boxId);
        growDto.setMushroomDtoList(mushroomDtoList);

        // Assert
        assertEquals(id, growDto.getId());
        assertEquals(date, growDto.getDate());
        assertEquals(stage, growDto.getStage());
        assertEquals(active, growDto.isActive());
        assertEquals(boxId, growDto.getBoxId());
        assertEquals(mushroomDtoList, growDto.getMushroomDtoList());
    }
}
