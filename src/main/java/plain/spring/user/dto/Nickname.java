package plain.spring.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Schema(description = "유저 닉네임")
@AllArgsConstructor
public class Nickname {
    private String nickname;
}