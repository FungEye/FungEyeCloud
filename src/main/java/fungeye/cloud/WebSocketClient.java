package fungeye.cloud;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketClient {
    public static void main(String[] args) {
        URI serverUri = null;
        try {
            serverUri = new URI("wss://iotnet.cibicom.dk/app?token=vnoUBgAAABFpb3RuZXQuY2liaWNvbS5ka12mjJpW808sXOBcROi7698=");
        } catch (URISyntaxException e) {
            System.err.println("Invalid URI");
            System.exit(1);
        }

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try (Session session = container.connectToServer(MyClientEndpoint.class, serverUri)) {
            session.getBasicRemote().sendText("Hello, WebSocket server!");
            Thread.sleep(3000); // Wait for a while to receive messages from the server
        } catch (IOException | DeploymentException | InterruptedException e) {
            System.err.println("Error during WebSocket connection: " + e.getMessage());
        }
    }
}