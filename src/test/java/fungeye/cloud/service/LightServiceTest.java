package fungeye.cloud.service;

import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.websockets.HardwareTutorial;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LightServiceTest {
    @Mock
    private HardwareTutorial hardwareTutorial;

    @Mock
    private BoxRepository boxRepository;

    private LightService lightService;

    public LightServiceTest() {
        MockitoAnnotations.openMocks(this);
        lightService = new LightService(hardwareTutorial, boxRepository);
    }

    @Test
    public void testToggleLight() {
        // Mock the necessary dependencies
        // Test for when the eui is used
//        Box box = new Box();
//        box.setEui("0004A30B00ED6757");
//        when(boxRepository.findById(anyLong())).thenReturn(Optional.of(box));

        // Perform the test
        boolean result = lightService.toggleLight(1);

        // Verify the interaction with the mock
        String expectedJson = "{\"cmd\" : \"tx\",\"EUI\" : \"0004A30B00ED6757\",\"port\": 1,\"confirmed\" : true,\"data\": \"01\"}";
        Mockito.verify(hardwareTutorial).sendDownLink(expectedJson);

        // Verify the result
        assertTrue(result);
    }

}