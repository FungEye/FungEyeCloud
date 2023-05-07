package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginDtoTest {

    private final String username = "john";
    private final String password = "pass123ff";
    private UserLoginDto dto;

    @BeforeEach
    void setUp() {
        dto = new UserLoginDto(username, password);
    }

    @Test
    void testConstructorWithUserAndPass() {
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testGettersAndSetters() {

        assertEquals(username, dto.getUsername());

        assertEquals(password, dto.getPassword());
    }

    @Test
    void testEquals() {
        UserLoginDto toCompare = new UserLoginDto(username, password);
        assertEquals(dto, toCompare);
    }

    @Test
    void canEqual() {
        assertTrue(dto.canEqual(new UserLoginDto(username, password)));
    }

    @Test
    void testToString() {
        assertEquals("UserLoginDto(username=john, password=pass123ff)", dto.toString());
    }
}