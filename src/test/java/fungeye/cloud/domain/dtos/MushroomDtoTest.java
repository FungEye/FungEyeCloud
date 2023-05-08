package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
