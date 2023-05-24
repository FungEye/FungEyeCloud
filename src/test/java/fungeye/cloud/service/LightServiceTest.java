package fungeye.cloud.service;

import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.websockets.HardwareTutorial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LightServiceTest {
    @Mock
    HardwareTutorial hardwareTutorial;

    @Mock
    BoxRepository boxRepository;

    private LightService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new LightService(hardwareTutorial, boxRepository);
    }

    @Test
    void testToggleLight() {
        // Todo: Mock web socket connection to test downlink message
    }

}