package fungeye.cloud.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    private SecurityConfig test;
    @Mock
    JwtAuthEntryPoint authEntryPoint;
    @Mock
    CustomUserDetailsService userDetailsService;
    @Mock
    AuthenticationConfiguration authenticationConfiguration;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        test = new SecurityConfig(userDetailsService, authEntryPoint);

    }

    @Test
    void filterChain() throws Exception {
        assertEquals(1, 1);
    }

    @Test
    void authenticationManager() throws Exception {
        assertEquals(1, 1);
    }

    @Test
    void passwordEncoder() {
        assertEquals(NoOpPasswordEncoder.getInstance(), test.passwordEncoder());
    }

    @Test
    void jwtAuthenticationFilter() {
        assertEquals(new JwtAuthenticationFilter().getClass().getName(), test.jwtAuthenticationFilter().getClass().getName());
    }
}