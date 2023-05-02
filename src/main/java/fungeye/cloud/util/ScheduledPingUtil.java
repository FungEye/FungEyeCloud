package fungeye.cloud.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledPingUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledPingUtil.class);

    //@Scheduled(fixedRate = 300000)
    public void sendPing() {
        LOGGER.debug("Sending ping");
        RestTemplate rt = new RestTemplate();
        String uriLocal = "http://localhost:8080/ping/";
        String uri = "https://fungeye-383609.ey.r.appspot.com/ping/";
        // Only when running local instance
        //rt.getForEntity(uriLocal, String.class);
        // For cloud env
        //rt.getForEntity(uri, String.class);
    }
}
