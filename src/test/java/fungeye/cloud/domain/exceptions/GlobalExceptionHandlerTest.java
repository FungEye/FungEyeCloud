package fungeye.cloud.domain.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

class GlobalExceptionHandlerTest {

    @Test
    void handleNoSuchElementExceptionTest() {
        // Arrange
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        WebRequest webRequest = mock(WebRequest.class);
        NoSuchElementException noSuchElementException = new NoSuchElementException("Element not found");

        // Act
        ResponseEntity<ErrorObject> responseEntity = globalExceptionHandler.handleNoSuchElementException(noSuchElementException, webRequest);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Element not found", responseEntity.getBody().getMessage());
    }

    @Test
    void handleUndefinedExceptionTest() {
        // Arrange
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        WebRequest webRequest = mock(WebRequest.class);
        Exception exception = new Exception("Undefined exception");

        // Act
        ResponseEntity<ErrorObject> responseEntity = globalExceptionHandler.handleUndefinedException(exception, webRequest);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Undefined exception", responseEntity.getBody().getMessage());
    }


    @Test
    void handleNotUniqueException() {
        // Arrange
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        WebRequest webRequest = mock(WebRequest.class);
        NotUniqueException exception = new NotUniqueException("Not Unique");

        // Act
        ResponseEntity<ErrorObject> responseEntity = globalExceptionHandler.handleNotUniqueException(exception, webRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Not Unique", responseEntity.getBody().getMessage());
    }

    @Test
    void handleBadCredentialsException() {
        // Arrange
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        WebRequest webRequest = mock(WebRequest.class);
        BadCredentialsException exception = new BadCredentialsException("Bad credentials");

        // Act
        ResponseEntity<ErrorObject> responseEntity = globalExceptionHandler.handleBadCredentialsException(exception, webRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Bad credentials", responseEntity.getBody().getMessage());
    }


}
