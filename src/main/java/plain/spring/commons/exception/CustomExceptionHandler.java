package plain.spring.commons.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity exceptionHandler(CustomException e){
        System.out.println("error code " + e.getErrorCode().getHttpStatus().value());
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponseBody.builder()
                        .status(e.getErrorCode().getHttpStatus().value())
                        .code(e.getErrorCode().name())
                        .message(e.getMessage())
                        .build());
    }
}
