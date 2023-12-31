package plain.spring.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "유저 정보 수정")
@AllArgsConstructor
@Builder
public class UserInfoEdit {
    @Schema(description = "유저 닉네임")
    private String nickname;
    @Schema(description = "유저 프로필 이미지 url")
    private String profileImageUrl;
    @Schema(description = "유저 배경 이미지 url")
    private String backgroundImageUrl;
    @Schema(description = "유저 태그 리스트")
    private List<String> tags;
    @Schema(description = "유저 직업 리스트")
    private List<String> jobs;
    @Schema(description = "email 주소")
    private String email;
    @Schema(description = "카카오톡 오픈 채팅 url")
    private String openChatUrl;
}
