package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserCreationDtoTest {

    private final String username = "john";
    private final String password = "pass123ff";
    private UserCreationDto userCreationDto;

    @BeforeEach
    void setUp() {
        userCreationDto = new UserCreationDto(username, password);
    }

    @Test
    void testConstructorWithUserAndPass() {
        assertEquals(username, userCreationDto.getUsername());
    }

    @Test
    void testGettersAndSetters() {
        userCreationDto.setUsername("doe");
        assertEquals("doe", userCreationDto.getUsername());

        userCreationDto.setPassword("newPass123");
        assertEquals("newPass123", userCreationDto.getPassword());
    }


}