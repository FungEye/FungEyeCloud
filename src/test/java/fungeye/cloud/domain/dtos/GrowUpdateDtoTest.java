package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.dtos.grow.GrowUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrowUpdateDtoTest {
    private GrowUpdateDto growUpdateDto;

    @BeforeEach
    void setup() {
        growUpdateDto = new GrowUpdateDto();
    }

    @Test
    void testValidDevelopmentStage() {
        growUpdateDto.setDevelopStage("spawn run");
        growUpdateDto.setDevelopmentStage("pinning");
        growUpdateDto.setDevelopmentStage("fruiting");

        assertEquals("fruiting", growUpdateDto.getDevelopStage());
    }

    @Test
    void testInvalidDevelopmentStage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            growUpdateDto.setDevelopmentStage("It's not a phase! I'll be a spore forever");
        });

        assertEquals("Not a valid development stage", exception.getMessage());
    }
}