package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.dtos.ideal.IdealConditionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IdealConditionDtoTest {

    @Test
    void testInvalidDevelopmentStage() {
        IdealConditionDto dto = new IdealConditionDto();
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            dto.setDevelopmentStage("Angsty teenage fungus");
        });

        Assertions.assertEquals("Not a valid development stage", thrown.getMessage());
    }

    @Test
    void testValidDevelopmentStage() {
        IdealConditionDto dto = new IdealConditionDto();
        dto.setDevelopmentStage("spawn run");

        assertEquals("spawn run", dto.getDevelopmentStage());
    }
}
