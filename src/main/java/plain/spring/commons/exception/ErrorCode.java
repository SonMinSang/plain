package plain.spring.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    NOT_VALID_HEADER(HttpStatus.BAD_REQUEST, "올바른 헤더값이 아닙니다."),
    NOT_VALID_VALUE(HttpStatus.BAD_REQUEST, "올바른 값이 아닙니다."),

    NOT_MEMBER(HttpStatus.UNAUTHORIZED, "회원이 아닙니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증에 실패 했습니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다"),

    BLOCKED_USER(HttpStatus.UNAUTHORIZED, "차단된 유저입니다."),

    ART_NOT_FOUND(HttpStatus.NOT_FOUND, "제품을 찾을 수 없습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 정보의 사용자를 찾을 수 없습니다."),
    EXHIBITION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 전시를 찾을 수 없습니다."),
    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "알림을 찾을 수 없습니다."),

    ALREADY_ARTIST_EXIST(HttpStatus.CONFLICT, "이미 존재하는 작가입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다.");
    private final HttpStatus httpStatus;
    private final String message;
}
