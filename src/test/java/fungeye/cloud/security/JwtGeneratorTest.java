package fungeye.cloud.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.Date;

import static fungeye.cloud.security.SecurityConstants.JWT_EXPIRATION;
import static fungeye.cloud.security.SecurityConstants.JWT_SECRET;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtGeneratorTest {

    private JwtGenerator test;
    @Mock
    Authentication auth;
    String token;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        test = new JwtGenerator();
        token = Jwts.builder()
                .setSubject("USERNAME")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
    @Test
    void generateToken() {
        when(auth.getName()).thenReturn("USERNAME");
        assertEquals(token, test.generateToken(auth));
    }

    @Test
    void getUsernameFromJwt() {
        assertEquals("USERNAME", test.getUsernameFromJwt(token));
    }

    @Test
    void validateToken() {
        assertTrue(test.validateToken(token));
    }
}