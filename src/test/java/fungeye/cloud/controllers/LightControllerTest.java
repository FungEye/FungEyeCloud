package fungeye.cloud.controllers;

import fungeye.cloud.service.LightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class LightControllerTest {

    @Mock
    LightService lightService;

    private LightController controller;

    @BeforeEach
    void setup() {
        openMocks(this);
        controller = new LightController(lightService);
    }

    @Test
    void testToggleLight() {
        int testBoxId = 2;

        when(lightService.toggleLight(testBoxId)).thenReturn(true);

        ResponseEntity<Boolean> response = controller.toggleLight(testBoxId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody());
    }
}