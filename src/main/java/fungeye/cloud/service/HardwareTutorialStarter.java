package fungeye.cloud.service;

import fungeye.cloud.HardwareTutorial;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HardwareTutorialStarter {
    @Autowired
    private HardwareTutorial hardwareTutorial;
    private MeasuredConditionsService service;

    public HardwareTutorialStarter(MeasuredConditionsService service) {
        this.service = service;
    }

    // post construct will launch this start method whenever spring boot application starts
    //@PostConstruct
    public void start() {
        hardwareTutorial = new HardwareTutorial(service);
    }
}
