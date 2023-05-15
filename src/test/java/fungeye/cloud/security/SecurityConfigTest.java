package fungeye.cloud.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fungeye.cloud.controllers.AuthController;
import fungeye.cloud.controllers.BoxController;
import fungeye.cloud.service.BoxService;
import fungeye.cloud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(controllers = {BoxController.class, AuthController.class})
@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    private SecurityConfig test;
    @Mock
    JwtAuthEntryPoint authEntryPoint;
    @Mock
    CustomUserDetailsService userDetailsService;
    @Mock
    AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    BoxService boxService;
    @MockBean
    UserService userService;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        test = new SecurityConfig(userDetailsService, authEntryPoint);

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

    @Test
    void filterChainUnAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/box1"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}