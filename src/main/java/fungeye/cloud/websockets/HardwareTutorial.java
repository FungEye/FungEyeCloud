package fungeye.cloud.websockets;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.MeasuredConditionIdDto;
import fungeye.cloud.service.MeasuredConditionsService;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static fungeye.cloud.service.mappers.DateTimeMapper.mapToDateDto;

@Component
public class HardwareTutorial implements WebSocket.Listener {
    private WebSocket server = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(HardwareTutorial.class);
    @Autowired
    private MeasuredConditionsService measurementService;

    private final String iotUrl = "wss://iotnet.cibicom.dk/app?token=vnoUBgAAABFpb3RuZXQuY2liaWNvbS5ka12mjJpW808sXOBcROi7698=";

    // Send down-link message to device
    // Must be in Json format according to https://github.com/ihavn/IoT_Semester_project/blob/master/LORA_NETWORK_SERVER.md
    public void sendDownLink(String jsonTelegram) {
        server.sendText(jsonTelegram, true);
    }

    // E.g. url: "wss://iotnet.teracom.dk/app?token=??????????????????????????????????????????????="
    // Substitute ????????????????? with the token you have been given
    public HardwareTutorial() {
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
        }
        //TODO: handle exceptions
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    //onOpen()
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        LOGGER.info("WebSocket Listener has been opened for requests.");
    }

    //onError()
    public void onError(WebSocket webSocket, Throwable error) {
        LOGGER.error("A " + error.getCause() + " exception was thrown.");
        LOGGER.error("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }


    //onClose()
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("WebSocket closed!");
        System.out.println("Status:" + statusCode + " Reason: " + reason);
        return new CompletableFuture().completedFuture("onClose() completed.").thenAccept(System.out::println);
    }

    ;

    //onPing()
    public CompletionStage<?> onPing​(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    ;

    //onPong()
    public CompletionStage<?> onPong​(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Pong: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return new CompletableFuture().completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    ;

    //onText()
    public CompletionStage<?> onText​(WebSocket webSocket, CharSequence data, boolean last) {
        String indented = null;
        String dataValue = null;
        Instant instant;
        try {
            JSONObject jsonObject = new JSONObject(data.toString());
            indented = jsonObject.toString(4);
            dataValue = jsonObject.optString("data"); // Extracts the "data" value from the JSON object
            String time = jsonObject.optString("time"); // Extracts the "time" value from the JSON object
            //timestamp = Timestamp.valueOf(time);
            long ts = jsonObject.getLong("ts"); // Extracts the "ts" (timestamp) value from the JSON object
            instant = Instant.ofEpochMilli(ts);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        // System.out.println(indented);

        // Check if "data" value is present and print it
        if (!dataValue.isEmpty()) {
            //System.out.println("Data value: " + dataValue);
            //radix 16 to show its converting from hex
            int humRaw = Integer.parseInt(dataValue.substring(0, 4), 16);
            int tempRaw = Integer.parseInt(dataValue.substring(4, 8), 16);
            int co2 = Integer.parseInt(dataValue.substring(8, 12), 16);//this is measured in ppm(parts per million)

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
        return new CompletableFuture().completedFuture("onText() completed.").thenAccept(LOGGER::info);
    }



/*

    public static void main(String[] args) {
        HardwareTutorial beep = new HardwareTutorial();
        // Assuming dataValue is "01160107041a"
        String testHex = "01160107041a";
        int humRaw = Integer.parseInt(testHex.substring(0, 4), 16);
        int tempRaw = Integer.parseInt(testHex.substring(4, 8), 16);
        int co2 = Integer.parseInt(testHex.substring(8,12),16);

        double temperature = tempRaw / 10.0f;
        double humidity = humRaw / 10.0f;
        double CO2 = co2/1.0f;

        System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
        System.out.println("Humidity: " + String.format("%.2f", humidity) + "%");
        System.out.println("CO2: " + String.format("%.2f", CO2) + "ppm");
    while(true){
        //
    }


*/

    public WebSocket getServer() {
        return server;
    }
}

