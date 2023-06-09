package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.dtos.date.DateTimeDto;
import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.mushroom.MushroomDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrowDtoTest {

    @Test
    void testSettersAndGetters() {
        // Arrange
        Long id = 1L;
        DateTimeDto date = new DateTimeDto(2023, 4, 14, 10, 30, 0);
        String stage = "spawn run";
        boolean active = true;
        Long boxId = 2L;
        List<MushroomDto> mushroomDtoList = new ArrayList<>();

        GrowDto growDto = new GrowDto();

        // Act
        growDto.setId(id);
        growDto.setDate(date);
        growDto.setStage(stage);
        growDto.setActive(active);
        growDto.setBoxId(boxId);;

        // Assert
        assertEquals(id, growDto.getId());
        assertEquals(date, growDto.getDate());
        assertEquals(stage, growDto.getStage());
        assertEquals(active, growDto.isActive());
        assertEquals(boxId, growDto.getBoxId());
    }
}
