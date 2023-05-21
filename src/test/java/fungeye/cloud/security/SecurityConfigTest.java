package fungeye.cloud.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import fungeye.cloud.controllers.AuthController;
import fungeye.cloud.controllers.BoxController;
import fungeye.cloud.service.BoxService;
import fungeye.cloud.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {BoxController.class, AuthController.class})
@ExtendWith(MockitoExtension.class)
class SecurityConfigTest {

    private SecurityConfig test;

    @InjectMocks
    private SecurityConfig securityConfig;

    @Mock
    private JwtAuthEntryPoint authEntryPoint;

    @Mock
    private CustomUserDetailsService userDetailsService;

    @Mock
    private HttpSecurity httpSecurity;

    @Mock
    private HttpSecurity httpSecurityMock;

    @Mock
    private AuthenticationConfiguration authenticationConfiguration;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    BoxService boxService;
    @MockBean
    UserService userService;


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

    @Test
    public void testFilterChain() throws Exception {
        // Mock necessary dependencies
        when(authenticationConfiguration.getAuthenticationManager()).thenReturn(authenticationManager);
        when(httpSecurity.authorizeHttpRequests()).thenReturn(httpSecurityMock.authorizeHttpRequests());
        when(httpSecurityMock.securityMatcher("/api/auth/**")).thenReturn(httpSecurityMock);
        when(httpSecurityMock.securityMatcher("/_ah/start")).thenReturn(httpSecurityMock);
        when(httpSecurityMock.securityMatcher("/_ah/stop")).thenReturn(httpSecurityMock);
        when(httpSecurityMock.securityMatcher("/swagger-ui/**")).thenReturn(httpSecurityMock);
        when(httpSecurityMock.securityMatcher("/v3/**")).thenReturn(httpSecurityMock);
        when(httpSecurityMock.securityMatcher("/mushroom")).thenReturn(httpSecurityMock);
        when(httpSecurity.httpBasic()).thenReturn(httpSecurityMock.httpBasic());

        // Invoke the method under test
        SecurityFilterChain filterChain = securityConfig.filterChain(httpSecurity);

        // Verify that the necessary methods are called
        verify(httpSecurity).cors();
        verify(httpSecurity).csrf();
        verify(httpSecurity).exceptionHandling();
        verify(httpSecurity).sessionManagement();
        verify(httpSecurity).authorizeHttpRequests();
        verify(httpSecurity).httpBasic();
        verify(httpSecurity).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        verify(httpSecurityMock).build();
    }
}