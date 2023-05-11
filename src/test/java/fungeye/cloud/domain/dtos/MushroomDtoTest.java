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
        String origin = "Japan";
        int userId = 1;

        MushroomDto mushroomDto = new MushroomDto(id, name, description, origin, 1);

        assertEquals(id, mushroomDto.getId());
        assertEquals(name, mushroomDto.getName());
        assertEquals(description, mushroomDto.getDescription());
        assertEquals(origin, mushroomDto.getOrigin());
        assertEquals(userId, mushroomDto.getUserId());

        Long newId = 3L;
        String newName = "Portobello";
        String newDescription = "A large, meaty mushroom";
        String newOrigin = "France";

        mushroomDto.setId(newId);
        mushroomDto.setName(newName);
        mushroomDto.setDescription(newDescription);
        mushroomDto.setOrigin(newOrigin);

        assertThat(mushroomDto.getId()).isEqualTo(newId);
        assertThat(mushroomDto.getName()).isEqualTo(newName);
        assertThat(mushroomDto.getDescription()).isEqualTo(newDescription);
        assertThat(mushroomDto.getOrigin()).isEqualTo(newOrigin);

    }
    @Test
    void testHashCode() {
        MushroomDto dto1 = new MushroomDto(1L, "button", "white mushroom", "France", 0);
        MushroomDto dto2 = new MushroomDto(1L, "button", "white mushroom", "France", 0);
        MushroomDto dto3 = new MushroomDto(2L, "portobello", "brown mushroom", "French", 1);

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {
        MushroomDto dto = new MushroomDto(1L, "button", "white mushroom", "France", 0);
        String expected = "MushroomDto{id=1, name='button', description='white mushroom', origin='France', userId=0}";

        assertEquals(expected, dto.toString());
    }

}
