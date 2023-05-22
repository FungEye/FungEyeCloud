package fungeye.cloud.service;

import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.websockets.HardwareTutorial;
import org.springframework.stereotype.Service;

@Service
public class LightService {

    private HardwareTutorial hardwareTutorial;
    private BoxRepository boxRepository;

    public LightService(HardwareTutorial hardwareTutorial, BoxRepository boxRepository) {
        this.hardwareTutorial = hardwareTutorial;
        this.boxRepository = boxRepository;
    }

    public boolean toggleLight(int boxId) {
        // Todo: Get the EUI using the boxId
        // The EUI of the device is used, but will be taken from the box in the future
        String eui = "0004A30B00ED6757";

        // This payload is for toggling the light
        String dataPayload = "01";
        String jsonToSend = "{\"cmd\" : \"tx\",\"EUI\" : \"" +
                eui + "\",\"port\": 1,\"confirmed\" : true,\"data\": \"" + dataPayload + "\"}";
        hardwareTutorial.sendDownLink(jsonToSend);
        return true;
    }
}
