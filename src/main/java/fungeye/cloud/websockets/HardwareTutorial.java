package fungeye.cloud.websockets;

import fungeye.cloud.domain.dtos.measured.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.measured.MeasuredConditionIdDto;
import fungeye.cloud.service.MeasuredConditionsService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static fungeye.cloud.service.mappers.DateTimeMapper.mapToDateDto;
@Slf4j
@Component
public class HardwareTutorial implements WebSocket.Listener {
    private WebSocket server = null;

    private MeasuredConditionsService measurementService;

    private final String iotUrl = "wss://iotnet.cibicom.dk/app?token=vnoUBgAAABFpb3RuZXQuY2liaWNvbS5ka12mjJpW808sXOBcROi7698=";

    // Send down-link message to device
    // Must be in Json format according to https://github.com/ihavn/IoT_Semester_project/blob/master/LORA_NETWORK_SERVER.md
    public void sendDownLink(String jsonTelegram) {
        server.sendText(jsonTelegram, true);
        log.info("Downlink message sent");
    }

    // E.g. url: "wss://iotnet.teracom.dk/app?token=??????????????????????????????????????????????="
    // Substitute ????????????????? with the token you have been given
    @Autowired
    public HardwareTutorial(MeasuredConditionsService measurementService) {
        // Bypass ssl certs
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };
            sslContext.init(null, trustAllCerts, new SecureRandom());
            HttpClient client = HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .build();
            CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                    .buildAsync(URI.create(iotUrl), this);
            server = ws.join();
            this.measurementService = measurementService;
        }
        //TODO: handle exceptions
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //onOpen()
    @Override
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        log.info("WebSocket Listener has been opened for requests.");
    }

    //onError()
    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        log.error("A " + error.getCause() + " exception was thrown.");
        log.error("Message: " + error.getLocalizedMessage());
        log.error(Arrays.toString(error.getStackTrace()));
        webSocket.abort();
    }


    //onClose()
    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        log.info("WebSocket closed!");
        log.error("Status:" + statusCode + " Reason: " + reason);
        return CompletableFuture.completedFuture("onClose() completed.").thenAccept(log::info);
    }

    ;

    //onPing()
    @Override
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        log.info("Ping: Client ---> Server");
        log.info(message.asCharBuffer().toString());
        return CompletableFuture.completedFuture("Ping completed.").thenAccept(log::info);
    }

    ;

    //onPong()
    @Override
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        log.info("Pong: Client ---> Server");
        log.info(message.asCharBuffer().toString());
        return CompletableFuture.completedFuture("Pong completed.").thenAccept(log::info);
    }

    ;

    //onText()
    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        try {
            JSONObject object = new JSONObject(data.toString());
            if (object.getString("cmd").equals("rx")) {
                // IT IS A MEASUREMENT!
                log.info("Measurement received!");
                readAndAddMeasurement(object);
            }
            else if (object.getString("cmd").equals("tx")) {
                // IT IS AN ACKNOWLEDGEMENT!
                log.info("Acknowledgement received!");
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //TODO Ask Martin about if and why this is needed
        /*
        sendDownLink(
                "{\"cmd\" : \"tx\",\"EUI\" : \"0004A30B00ED6757\",\"port\": 1,\"confirmed\" : true,\"data\": \"11\"}"

        );
         */
        webSocket.request(1);
        return CompletableFuture.completedFuture("Data received successfully").thenAccept(log::info);
    }

    private void readAndAddMeasurement(JSONObject jsonObject) throws JSONException {
        String dataValue = jsonObject.optString("data"); // Extracts the "data" value from the JSON object
        long ts = jsonObject.getLong("ts"); // Extracts the "ts" (timestamp) value from the JSON object
        Instant instant = Instant.ofEpochMilli(ts);

        // Check if "data" value is present and print it
        if (!dataValue.isEmpty()) {
            //System.out.println("Data value: " + dataValue);
            //radix 16 to show its converting from hex
            int humRaw = Integer.parseInt(dataValue.substring(0, 4), 16);
            int tempRaw = Integer.parseInt(dataValue.substring(4, 8), 16);
            int co2 = Integer.parseInt(dataValue.substring(8, 12), 16);//this is measured in ppm(parts per million)
            int light = Integer.parseInt(dataValue.substring(12, 16), 16);

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
            condDto.setCo2((double) co2);
            condDto.setLight((double) light);
            // measurementService.addMeasuredCondition(condDto);
            // Using the below method to send a copy of the measurement to all active boxes
            measurementService.addMeasuredCondition(condDto);
        }
    }
}

