package fungeye.cloud.util;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

public class ScheduledPingUtilTest {

    @Test
    public void testSendPing() {
        Logger loggerMock = Mockito.mock(Logger.class);
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        ScheduledPingUtil scheduledPingUtil = new ScheduledPingUtil();

        scheduledPingUtil.sendPing();
        loggerMock.debug("Sending ping");
        restTemplateMock.getForEntity("https://fungeye-383609.ey.r.appspot.com/ping/", String.class);

        Mockito.verify(loggerMock).debug("Sending ping");
        Mockito.verify(restTemplateMock).getForEntity("https://fungeye-383609.ey.r.appspot.com/ping/", String.class);
    }
}
