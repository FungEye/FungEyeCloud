package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MushroomDtoTest {

    @Test
    public void testGettersAndSetters() {
        Long id = 1L;
        String name = "Shiitake";
        String description = "A type of edible mushroom";
        Long growId = 2L;

        MushroomDto mushroomDto = new MushroomDto(id, name, description, growId);

        assertThat(mushroomDto.getId()).isEqualTo(id);
        assertThat(mushroomDto.getName()).isEqualTo(name);
        assertThat(mushroomDto.getDescription()).isEqualTo(description);
        assertThat(mushroomDto.getGrowId()).isEqualTo(growId);

        Long newId = 3L;
        String newName = "Portobello";
        String newDescription = "A large, meaty mushroom";
        Long newGrowId = 4L;

        mushroomDto.setId(newId);
        mushroomDto.setName(newName);
        mushroomDto.setDescription(newDescription);
        mushroomDto.setGrowId(newGrowId);

        assertThat(mushroomDto.getId()).isEqualTo(newId);
        assertThat(mushroomDto.getName()).isEqualTo(newName);
        assertThat(mushroomDto.getDescription()).isEqualTo(newDescription);
        assertThat(mushroomDto.getGrowId()).isEqualTo(newGrowId);
    }
}
