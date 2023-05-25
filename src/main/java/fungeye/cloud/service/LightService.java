package fungeye.cloud.service;

import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.websockets.HardwareTutorial;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LightService {

    private HardwareTutorial hardwareTutorial;
    private BoxRepository boxRepository;

    public LightService(HardwareTutorial hardwareTutorial, BoxRepository boxRepository) {
        this.hardwareTutorial = hardwareTutorial;
        this.boxRepository = boxRepository;
    }

    public boolean toggleLight(int boxId) {
        // When more actual devices are used in the system, their EUI will be taken from the box
        // String eui = getEUIFromBoxId(boxId);

        String eui = "0004A30B00ED6757";

        // This payload is for toggling the light
        String dataPayload = "01";
        String jsonToSend = "{\"cmd\" : \"tx\",\"EUI\" : \"" +
                eui + "\",\"port\": 1,\"confirmed\" : true,\"data\": \"" + dataPayload + "\"}";
        hardwareTutorial.sendDownLink(jsonToSend);
        return true;
    }

    private String getEUIFromBoxId(int boxId) {
        Optional<Box> box = boxRepository.findById((long) boxId);
        if (box.isPresent()) {
            return box.get().getEui();
        } else {
            throw new IllegalArgumentException("No box was found with that id");
        }
    }
}
