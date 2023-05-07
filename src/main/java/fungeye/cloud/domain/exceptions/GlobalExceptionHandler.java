package fungeye.cloud.domain.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorObject> handleNoSuchElementException(NoSuchElementException e, WebRequest request) {
        ErrorObject eo = new ErrorObject();
        eo.setStatusCode(HttpStatus.NOT_FOUND.value());
        eo.setMessage(e.getMessage());
        eo.setTimestamp(new Date());

        logger.error(e.getMessage(), eo);
        return new ResponseEntity<>(eo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotUniqueException.class)
    public ResponseEntity<ErrorObject> handleNotUniqueException(NotUniqueException e, WebRequest request) {
        ErrorObject eo = new ErrorObject();
        eo.setStatusCode(HttpStatus.BAD_REQUEST.value());
        eo.setMessage(e.getMessage());
        eo.setTimestamp(new Date());

        return new ResponseEntity<>(eo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorObject> handleBadCredentialsException(BadCredentialsException e, WebRequest request) {
        ErrorObject eo = new ErrorObject();
        eo.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        eo.setMessage(e.getMessage());
        eo.setTimestamp(new Date());

        return new ResponseEntity<>(eo, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleUndefinedException(Exception e, WebRequest request) {
        ErrorObject eo = new ErrorObject();
        eo.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        eo.setMessage(e.getMessage());
        eo.setTimestamp(new Date());

        logger.error(e.getMessage(), eo);
        return new ResponseEntity<>(eo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
