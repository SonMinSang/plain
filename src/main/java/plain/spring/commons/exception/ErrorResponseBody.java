package plain.spring.commons.exception;

import lombok.Builder;


@Builder
public class ErrorResponseBody {
    private int status;
    private String code;
    private String message;

}
