package fungeye.cloud.service;

import fungeye.cloud.HardwareTutorial;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HardwareTutorialStarter {
    @Autowired
    private HardwareTutorial hardwareTutorial;

    // post construct will launch this start method whenever spring boot application starts
    @PostConstruct
    public void start() {
        hardwareTutorial = new HardwareTutorial();
        hardwareTutorial.onOpen(hardwareTutorial.getServer());
        while(true){
            hardwareTutorial.sendDownLink("Beep");
        }
    }
}
