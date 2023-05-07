package fungeye.cloud.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterchain;

    private JwtAuthenticationFilter test;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        test = new JwtAuthenticationFilter();
    }

    @Test
    void doFilterInternal() throws ServletException, IOException {
    assertDoesNotThrow(() -> test.doFilterInternal(request, response, filterchain));
    }
}