package restapp.exception_handler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    void exceptionHandlerShould_set400StatusCodeAndSendErrorMessage_ifAnyExceptionThrown() {
        var responseEntity = globalExceptionHandler.handleException(new Exception());
        Assertions.assertEquals(400, responseEntity.getStatusCode().value());
        Assertions.assertTrue(responseEntity.getBody().contains("Error message :"));
    }
}
