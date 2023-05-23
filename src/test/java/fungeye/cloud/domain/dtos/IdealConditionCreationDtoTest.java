package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.dtos.ideal.IdealConditionCreationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdealConditionCreationDtoTest {
    private IdealConditionCreationDto idealConditionCreationDto;

    @BeforeEach
    void setup() {
        idealConditionCreationDto = new IdealConditionCreationDto();
    }

    @Test
    void testValidDevelopmentStage() {
        idealConditionCreationDto.setDevelopmentStage("spawn run");
        idealConditionCreationDto.setDevelopmentStage("pinning");
        idealConditionCreationDto.setDevelopmentStage("fruiting");

        assertEquals("fruiting", idealConditionCreationDto.getDevelopmentStage());
    }

    @Test
    void testInvalidDevelopmentStage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            idealConditionCreationDto.setDevelopmentStage("It's not a phase! I'll be a spore forever");
        });

        assertEquals("Not a valid development stage", exception.getMessage());
    }

}