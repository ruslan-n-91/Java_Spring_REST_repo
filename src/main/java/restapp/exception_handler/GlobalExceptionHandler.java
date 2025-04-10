package restapp.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(produces = MediaType.APPLICATION_JSON_VALUE)
    //@ExceptionHandler(produces = MediaType.TEXT_PLAIN_VALUE)
    //@ExceptionHandler
    public String handleException(Exception ex) {
        return "{\"Message\":\"Error occurred\"}";
        //return ex.getMessage();
    }
}
