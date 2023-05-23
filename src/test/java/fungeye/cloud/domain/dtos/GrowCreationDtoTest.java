package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.dtos.grow.GrowCreationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GrowCreationDtoTest {

    @Mock
    private GrowCreationDto growCreationDto;

    @BeforeEach
    void setup() {
        growCreationDto = new GrowCreationDto();
    }

    @Test
    void testValidDevelopmentStage() {
        growCreationDto.setDevelopStage("spawn run");
        growCreationDto.setDevelopStage("pinning");
        growCreationDto.setDevelopStage("fruiting");

        assertEquals("fruiting", growCreationDto.getDevelopStage());
    }

    @Test
    void testInvalidDevelopmentStage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            growCreationDto.setDevelopStage("It's not a phase! I'll be a spore forever");
        });

        assertEquals("Not a valid development stage", exception.getMessage());
    }
}
