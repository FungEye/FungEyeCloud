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

        MushroomDto mushroomDto = new MushroomDto(id, name, description, origin);

        assertThat(mushroomDto.getId()).isEqualTo(id);
        assertThat(mushroomDto.getName()).isEqualTo(name);
        assertThat(mushroomDto.getDescription()).isEqualTo(description);
        assertThat(mushroomDto.getOrigin()).isEqualTo(origin);

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
        MushroomDto dto1 = new MushroomDto(1L, "button", "white mushroom", "France");
        MushroomDto dto2 = new MushroomDto(1L, "button", "white mushroom", "France");
        MushroomDto dto3 = new MushroomDto(2L, "portobello", "brown mushroom", "French");

        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {
        MushroomDto dto = new MushroomDto(1L, "button", "white mushroom", "France");
        String expected = "MushroomDto{id=1, name='button', description='white mushroom', origin='France'}";

        assertEquals(expected, dto.toString());
    }

}
