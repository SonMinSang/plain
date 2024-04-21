package plain.spring.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ErrorResponseBody {
    private int status;
    private String code;
    private String message;

}
