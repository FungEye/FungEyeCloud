package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthResponseDtoTest {

    private final String token = "token";
    private AuthResponseDto dto;

    @BeforeEach
    void setUp() {
        dto = new AuthResponseDto(token);
    }

    @Test
    void testConstructor() {
        assertEquals("Bearer: ", dto.getTokenType());
    }

    @Test
    void testGettersAndSetters() {

        assertEquals(token, dto.getAccessToken());
    }

}