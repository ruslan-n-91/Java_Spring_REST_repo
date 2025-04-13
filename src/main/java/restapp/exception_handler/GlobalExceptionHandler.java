package restapp.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> handleException(Exception ex) {
        String string = "Error occurred: " + ex.getClass().getSimpleName();
        return new ResponseEntity<>(string, HttpStatus.BAD_REQUEST);
    }
}
