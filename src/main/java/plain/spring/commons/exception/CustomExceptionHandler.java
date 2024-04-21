package plain.spring.commons.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity exceptionHandler(CustomException e){
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponseBody.builder()
                        .status(e.getErrorCode().getHttpStatus().value())
                        .code(e.getErrorCode().name())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler
    protected ResponseEntity exceptionHandler(RuntimeException e){
        return ResponseEntity
                .status(500)
                .body(ErrorResponseBody.builder()
                        .status(500)
                        .code("RuntimeException")
                        .message(e.getMessage())
                        .build());
    }
}
