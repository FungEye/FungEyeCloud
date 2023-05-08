package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MushroomDtoTest {

    @Test
    void testGettersAndSetters() {
        Long id = 1L;
        String name = "Shiitake";
        String description = "A type of edible mushroom";
        Long growId = 2L;

        MushroomDto mushroomDto = new MushroomDto(id, name, description);

        assertThat(mushroomDto.getId()).isEqualTo(id);
        assertThat(mushroomDto.getName()).isEqualTo(name);
        assertThat(mushroomDto.getDescription()).isEqualTo(description);

        Long newId = 3L;
        String newName = "Portobello";
        String newDescription = "A large, meaty mushroom";
        Long newGrowId = 4L;

        mushroomDto.setId(newId);
        mushroomDto.setName(newName);
        mushroomDto.setDescription(newDescription);

        assertThat(mushroomDto.getId()).isEqualTo(newId);
        assertThat(mushroomDto.getName()).isEqualTo(newName);
        assertThat(mushroomDto.getDescription()).isEqualTo(newDescription);
    }
    @Test
    void testHashCode() {
        MushroomDto dto1 = new MushroomDto(1L, "button", "white mushroom");
        MushroomDto dto2 = new MushroomDto(1L, "button", "white mushroom");
        MushroomDto dto3 = new MushroomDto(2L, "portobello", "brown mushroom");

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {
        MushroomDto dto = new MushroomDto(1L, "button", "white mushroom");
        String expected = "MushroomDto{id=1, name='button', description='white mushroom'}";

        assertEquals(expected, dto.toString());
    }

}
