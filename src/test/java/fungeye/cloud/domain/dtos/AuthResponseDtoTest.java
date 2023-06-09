package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.dtos.auth.AuthResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void setAccessToken() {
        dto.setAccessToken("NEW TOKEN");
        assertEquals("NEW TOKEN", dto.getAccessToken());
    }

    @Test
    void setTokenType() {
        dto.setTokenType("NEW");
        assertEquals("NEW", dto.getTokenType());
    }

    @Test
    void testEquals() {
        AuthResponseDto toCompare = new AuthResponseDto("token");
        assertEquals(dto, toCompare);
    }
}