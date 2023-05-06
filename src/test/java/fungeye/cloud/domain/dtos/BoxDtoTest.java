package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BoxDtoTest {

    @Test
    void testConstructorWithId() {
        Long id = 1L;
        BoxDto boxDto = new BoxDto(id);
        assertEquals(id, boxDto.getId());
    }

    @Test
    void testDefaultConstructor() {
        BoxDto boxDto = new BoxDto();
        assertNull(boxDto.getId());
    }

    @Test
    void testSetId() {
        Long id = 1L;
        BoxDto boxDto = new BoxDto();
        boxDto.setId(id);
        assertEquals(id, boxDto.getId());
    }

    @Test
    void testGetId() {
        Long id = 1L;
        BoxDto boxDto = new BoxDto(id);
        assertEquals(id, boxDto.getId());
    }
}
