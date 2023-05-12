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
}
