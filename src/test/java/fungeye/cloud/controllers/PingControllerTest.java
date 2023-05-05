package fungeye.cloud.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PingControllerTest.class)
class PingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private Logger logger;

    @InjectMocks PingController pingController;

    @Test
    public void ping() throws Exception {
        PingController pingController = new PingController();
        ResponseEntity<String> response = pingController.ping();
        assert(response.getStatusCode()).equals(HttpStatus.OK);
        assert Objects.equals(response.getBody(), "");
    }

    @Test
    public void ensureLogs() {
        logger.debug("Testing logger");
        verify(logger).debug("Testing logger");
    }

}