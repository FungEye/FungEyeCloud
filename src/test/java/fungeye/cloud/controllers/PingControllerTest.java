package fungeye.cloud.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.mockito.Mockito.verify;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PingControllerTest.class)
class PingControllerTest {
    @InjectMocks
    PingController pingController;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private Logger logger;

    @Test
    public void ping() throws Exception {
        PingController pingController = new PingController();
        ResponseEntity<String> response = pingController.ping();
        assertEquals("Http OK", response.getStatusCode(), HttpStatus.OK);
        assertEquals("Body empty", response.getBody(), "");
    }

    @Test
    public void ensureLogs() {
        logger.debug("Testing logger");
        verify(logger).debug("Testing logger");
    }

}