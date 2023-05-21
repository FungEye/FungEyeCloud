package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Assertions;

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
        growCreationDto.setDevelopmentStage("pinning");
        growCreationDto.setDevelopmentStage("fruiting");

        assertEquals("fruiting", growCreationDto.getDevelopStage());
    }

    @Test
    void testInvalidDevelopmentStage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            growCreationDto.setDevelopmentStage("It's not a phase! I'll be a spore forever");
        });

        assertEquals("Not a valid development stage", exception.getMessage());
    }
}