package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MushroomDtoTest {

    @Test
    void testGettersAndSetters() {
        MushroomDto mushroomDto = new MushroomDto();

        Long newId = 2L;
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
        MushroomDto dto1 = new MushroomDto();
        dto1.setId(1L);
        dto1.setName("button");
        dto1.setDescription("white mushroom");
        dto1.setOrigin("France");
        dto1.setUserId(2);
        MushroomDto dto2 = new MushroomDto();
        dto2.setId(1L);
        dto2.setName("button");
        dto2.setDescription("white mushroom");
        dto2.setOrigin("France");
        dto2.setUserId(2);
        MushroomDto dto3 = new MushroomDto();
        dto3.setId(1L);
        dto3.setName("portobello");
        dto3.setDescription("brown mushroom");
        dto3.setOrigin("French");
        dto3.setUserId(1);


        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto2.hashCode(), dto3.hashCode());
    }

    @Test
    void testToString() {
        MushroomDto dto = new MushroomDto();
        dto.setId(1L);
        dto.setName("button");
        dto.setDescription("white mushroom");
        dto.setOrigin("France");
        dto.setUserId(2);
        String expected = "MushroomDto{id=1, name='button', description='white mushroom', origin='France', userId=2}";

        assertEquals(expected, dto.toString());
    }

}
