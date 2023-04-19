package fungeye.cloud;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Component
public class HardwareTutorial implements WebSocket.Listener {
    private WebSocket server = null;
    double temp;
    double hum;


    private final String iotUrl = "wss://iotnet.cibicom.dk/app?token=vnoUBgAAABFpb3RuZXQuY2liaWNvbS5ka12mjJpW808sXOBcROi7698=";

    // Send down-link message to device
    // Must be in Json format according to https://github.com/ihavn/IoT_Semester_project/blob/master/LORA_NETWORK_SERVER.md
    public void sendDownLink(String jsonTelegram) {
        server.sendText(jsonTelegram, true);
    }

    // E.g. url: "wss://iotnet.teracom.dk/app?token=??????????????????????????????????????????????="
    // Substitute ????????????????? with the token you have been given
    public HardwareTutorial() {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<WebSocket> ws = client.newWebSocketBuilder()
                .buildAsync(URI.create(iotUrl), this);

        server = ws.join();
    }

    //onOpen()
    public void onOpen(WebSocket webSocket) {
        // This WebSocket will invoke onText, onBinary, onPing, onPong or onClose methods on the associated listener (i.e. receive methods) up to n more times
        webSocket.request(1);
        System.out.println("WebSocket Listener has been opened for requests.");
    }

    //onError()
    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println("A " + error.getCause() + " exception was thrown.");
        System.out.println("Message: " + error.getLocalizedMessage());
        webSocket.abort();
    }

    ;

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
        Timestamp timestamp;
        try {
            JSONObject jsonObject = new JSONObject(data.toString());
            indented = jsonObject.toString(4);
            dataValue = jsonObject.optString("data"); // Extracts the "data" value from the JSON object
            String time = jsonObject.optString("time"); // Extracts the "time" value from the JSON object
            timestamp = Timestamp.valueOf(time);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        System.out.println(indented);

        // Check if "data" value is present and print it
        if (!dataValue.isEmpty()) {
            System.out.println("Data value: " + dataValue);
            //radix 16 to show its converting from hex
            int humRaw = Integer.parseInt(dataValue.substring(0, 4), 16);
            int tempRaw = Integer.parseInt(dataValue.substring(4, 8), 16);

            double temperature = tempRaw / 10.0f;
            double humidity = humRaw / 10.0f;


            System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
            System.out.println("Humidity: " + String.format("%.2f", humidity) + "%");
            System.out.println(timestamp);

        }
        webSocket.request(1);
        return new CompletableFuture().completedFuture("onText() completed.").thenAccept(System.out::println);
    }


    ;

    public static void main(String[] args) {
        HardwareTutorial beep = new HardwareTutorial();
        // Assuming dataValue is "01160107041a"
        String testHex = "01160107041a";
        int humRaw = Integer.parseInt(testHex.substring(0, 4), 16);
        int tempRaw = Integer.parseInt(testHex.substring(4, 8), 16);

        double temperature = tempRaw / 10.0f;
        double humidity = humRaw / 10.0f;

        System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
        System.out.println("Humidity: " + String.format("%.2f", humidity) + "%");
    while(true){
        //
    }



/*
        int humRaw = Integer.parseInt(testHex.substring(0, 4), 16);
        int tempRaw = Integer.parseInt(testHex.substring(4, 8), 16);
        tempRaw = tempRaw/10;
        humRaw = humRaw/10;

        System.out.println(tempRaw + "Temp");
        System.out.println(humRaw + "Hum");
        while(true){

        }



 */


    }

    public WebSocket getServer() {
        return server;
    }
}
