package fungeye.cloud.websockets;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.MeasuredConditionIdDto;
import fungeye.cloud.service.MeasuredConditionsService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static fungeye.cloud.service.mappers.DateTimeMapper.mapToDateDto;

@Component
public class HardwareTutorial implements WebSocket.Listener {
    private WebSocket server = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(HardwareTutorial.class);
    private final MeasuredConditionsService measurementService;

    private static final String iotUrl = "wss://iotnet.cibicom.dk/app?token=vnoUBgAAABFpb3RuZXQuY2liaWNvbS5ka12mjJpW808sXOBcROi7698=";

    // Send down-link message to device
    // Must be in Json format according to https://github.com/ihavn/IoT_Semester_project/blob/master/LORA_NETWORK_SERVER.md
    public void sendDownLink(String jsonTelegram) {
        server.sendText(jsonTelegram, true);
    }

    // E.g. url: "wss://iotnet.teracom.dk/app?token=??????????????????????????????????????????????="
    // Substitute ????????????????? with the token you have been given
    public HardwareTutorial(MeasuredConditionsService service) {
        this.measurementService = service;
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(iotUrl), this);

        server = ws.join();
    }

    //onOpen()
    @Override
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        LOGGER.info("WebSocket Listener has been opened for requests.");
    }

    //onError()
    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        LOGGER.error("A " + error.getCause() + " exception was thrown.");
        LOGGER.error("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }


    //onClose()
    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        LOGGER.info("WebSocket closed! Status code: {}, Reason {}", statusCode, reason);
        return CompletableFuture.completedFuture("onClose() completed.").thenAccept(LOGGER::info);
    }

    //onPing()
    @Override
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        LOGGER.info("Ping: Client ---> Server");
        LOGGER.info(message.asCharBuffer().toString());
        return CompletableFuture.completedFuture("Ping completed.").thenAccept(LOGGER::info);
    }

    ;

    //onPong()
    @Override
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        LOGGER.info("Pong: Client ---> Server");
        LOGGER.info(message.asCharBuffer().toString());
        return CompletableFuture.completedFuture("Pong completed.").thenAccept(LOGGER::info);
    }


    //onText()
    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        String dataValue;
        Instant instant;
        try {
            JSONObject jsonObject = new JSONObject(data.toString());
            dataValue = jsonObject.optString("data"); // Extracts the "data" value from the JSON object
            long ts = jsonObject.getLong("ts"); // Extracts the "ts" (timestamp) value from the JSON object
            instant = Instant.ofEpochMilli(ts);
        } catch (JSONException e) {
            //TODO add dedicated exception to be handled here
            throw new RuntimeException(e);
        }

        // Check if "data" value is present and print it
        if (!dataValue.isEmpty()) {
            //radix 16 to show its converting from hex
            int humRaw = Integer.parseInt(dataValue.substring(0, 4), 16);
            int tempRaw = Integer.parseInt(dataValue.substring(4, 8), 16);

            double temperature = tempRaw / 10.0;
            double humidity = humRaw / 10.0;

            MeasuredConditionIdDto idDto = new MeasuredConditionIdDto();
            /*
            This is a quick (... hacky) solution to getting the box id. In the future, we should probably add the EUID from the box
             as a value that we store in the db, and then lookup the actual box.
             But since we only have 1 functional box, this should do the trick for now at least.
             */
            idDto.setBoxId(1L);
            idDto.setDateTime(mapToDateDto(instant));

            MeasuredConditionDto condDto = new MeasuredConditionDto();
            condDto.setId(idDto);
            condDto.setHumidity(humidity);
            condDto.setTemperature(temperature);
            measurementService.addMeasuredCondition(condDto);
        }
        webSocket.request(1);
        return CompletableFuture.completedFuture("onText() completed.").thenAccept(LOGGER::info);
    }

    public WebSocket getServer() {
        return server;
    }
}
