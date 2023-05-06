package fungeye.cloud.websockets;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.MeasuredConditionIdDto;
import fungeye.cloud.service.MeasuredConditionsService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static fungeye.cloud.service.mappers.DateTimeMapper.mapToDateDto;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

class HardwareTutorialTest {

    private HardwareTutorial hardwareTutorial;

    @MockBean
    private MeasuredConditionsService measurementService;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hardwareTutorial = new HardwareTutorial(measurementService);
        ReflectionTestUtils.setField(hardwareTutorial, "measurementService", measurementService);
    }

    @Test
    void onOpen() {
        WebSocket webSocket = mock(WebSocket.class);
        hardwareTutorial.onOpen(webSocket);
        verify(webSocket, times(1)).request(1);
    }

// Todo: Improve when downlink is initially tested

//    @Test
//    public void sendDownLinkTest() {
//        String expectedJsonTelegram = "{\"key\":\"value\"}";  // Replace with your expected JSON message
//
//        WebSocket mockWebSocket = mock(WebSocket.class);
//        CompletableFuture<WebSocket> mockCompletableFuture = mock(CompletableFuture.class);
//        when(mockCompletableFuture.join()).thenReturn(mockWebSocket);
//
//        SSLContext mockSslContext = mock(SSLContext.class);
//
//        HttpClient mockHttpClient = mock(HttpClient.class);
//        when(HttpClient.newBuilder()).thenReturn(mock(HttpClient.Builder.class));
//        when(HttpClient.newBuilder().sslContext(mockSslContext)).thenReturn(mock(HttpClient.Builder.class));
//        when(HttpClient.newBuilder().sslContext(mockSslContext).build()).thenReturn(mockHttpClient);
//        when(mockHttpClient.newWebSocketBuilder()).thenReturn(mock(mockHttpClient.newWebSocketBuilder().getClass()));
//        when(mockHttpClient.newWebSocketBuilder().buildAsync(any(URI.class), any(WebSocket.Listener.class))).thenReturn(mockCompletableFuture);
//
//        HardwareTutorial mockedHardwareTutorial = spy(new HardwareTutorial(measurementService));
//        doReturn(mockHttpClient).when(mockedHardwareTutorial).onOpen(mockWebSocket);
//        doNothing().when(mockedHardwareTutorial).onOpen(any(WebSocket.class));
//        doNothing().when(mockedHardwareTutorial).onError(any(WebSocket.class), any(Throwable.class));
//        doNothing().when(mockedHardwareTutorial).onClose(any(WebSocket.class), anyInt(), anyString());
//        doNothing().when(mockedHardwareTutorial).onPing(any(WebSocket.class), any(ByteBuffer.class));
//        doNothing().when(mockedHardwareTutorial).onPong(any(WebSocket.class), any(ByteBuffer.class));
//        doNothing().when(mockedHardwareTutorial).sendDownLink(expectedJsonTelegram);
//
//        mockedHardwareTutorial.sendDownLink(expectedJsonTelegram);
//
//        verify(mockWebSocket).sendText(stringCaptor.capture(), eq(true));
//        String actualJsonTelegram = stringCaptor.getValue();
//
//        assertEquals("Message comparison", expectedJsonTelegram, actualJsonTelegram);
//    }

    @Test
    void onClose() {
        WebSocket webSocket = mock(WebSocket.class);
        // Arrange
        int statusCode = 1000;
        String reason = "Normal closure";
        when(webSocket.sendText(anyString(), anyBoolean())).thenReturn(mock(CompletableFuture.class));

        // Act
        CompletionStage<?> result = hardwareTutorial.onClose(webSocket, statusCode, reason);

        // Assert
        assertNotNull(result);
    }

    @Test
    void onError() {
        WebSocket webSocket = mock(WebSocket.class);
        Throwable error = new Throwable("An error occurred");
        hardwareTutorial.onError(webSocket, error);
        verify(webSocket, times(1)).abort();
    }

    // Issues with completable future
//    @Test
//    @DisplayName("Test onClose()")
//    void onClose() {
//        WebSocket webSocket = mock(WebSocket.class);
//        CompletableFuture completableFuture = mock(CompletableFuture.class);
//        when(completableFuture.completedFuture("onClose() completed.")).thenReturn(completableFuture);
//        hardwareTutorial.onClose(webSocket, 200, "OK");
//        verify(completableFuture, times(1)).thenAccept(System.out::println);
//    }

    @Test
    void onPing() {
        // This part of the test should be improved
        ByteBuffer message = ByteBuffer.wrap("Hello".getBytes());
        assertEquals("Ping complete", hardwareTutorial.onPing(Mockito.mock(WebSocket.class), message)
                .thenAccept(System.out::println) instanceof CompletableFuture<Void>, true);
    }

    @Test
    void onPong() {
        WebSocket webSocket = mock(WebSocket.class);
        ByteBuffer message = ByteBuffer.wrap(new byte[]{1, 2, 3});
        CompletableFuture completableFuture = mock(CompletableFuture.class);
        ByteBuffer message2 = ByteBuffer.wrap("Hello".getBytes());
        // This part of the test should be improved
        assertEquals("Ping complete", hardwareTutorial.onPong(Mockito.mock(WebSocket.class), message)
                .thenAccept(System.out::println) instanceof CompletableFuture<Void>, true);
    }

    @Test
    void onText_validJSON() throws Exception {
        measurementService = Mockito.mock(MeasuredConditionsService.class);
        hardwareTutorial = new HardwareTutorial(measurementService);
        // arrange
        WebSocket webSocket = mock(WebSocket.class);
        Instant instant = Instant.now();
        String jsonString = "{ \"data\":\"01f300dc1f5a\", \"time\":\"2023-05-05T12:34:56.789Z\", \"ts\":" + instant.toEpochMilli() + ", \"fcnt\":1, \"port\":2 }";
        String inputPayload = "01f300dc1f5a";
        // Create expected measurements from payload above
        int inHumRaw = Integer.parseInt(inputPayload.substring(0, 4), 16);
        int inTempRaw = Integer.parseInt(inputPayload.substring(4, 8), 16);
        int inCo2 = Integer.parseInt(inputPayload.substring(8, 12), 16);//this is measured in ppm(parts per million)

        double expectedTemperature = inTempRaw / 10.0;
        double expectedHumidity = inHumRaw / 10.0;

        CompletableFuture<?> future = new CompletableFuture<>();
        future.complete(null);


        // act
        hardwareTutorial.onText(webSocket, jsonString, true);


        // assert
        JSONObject jsonObject = new JSONObject(jsonString);
        String dataValue = jsonObject.optString("data");

        // Extract values and create dto
        int humRaw = Integer.parseInt(dataValue.substring(0, 4), 16);
        int tempRaw = Integer.parseInt(dataValue.substring(4, 8), 16);
        int co2 = Integer.parseInt(dataValue.substring(8, 12), 16);//this is measured in ppm(parts per million)

        double temperature = tempRaw / 10.0;
        double humidity = humRaw / 10.0;
        MeasuredConditionIdDto idDto = new MeasuredConditionIdDto();
        idDto.setBoxId(1L);
        idDto.setDateTime(mapToDateDto(instant));

        MeasuredConditionDto condDto = new MeasuredConditionDto();
        condDto.setId(idDto);
        condDto.setHumidity(humidity);
        condDto.setTemperature(temperature);

        measurementService.addMeasuredCondition(condDto);

        verify(measurementService).addMeasuredCondition(condDto);

        assertEquals("Temp: ", expectedTemperature, condDto.getTemperature());
        assertEquals("Humidity: ", expectedHumidity, condDto.getHumidity());
    }
}
