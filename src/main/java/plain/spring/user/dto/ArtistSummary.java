package plain.spring.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.user.domain.User;

@Getter
@NoArgsConstructor
@Schema(description = "유저 summary")
@AllArgsConstructor
public class ArtistSummary {
    @Schema(description = "유저 id")
    private Long id;
    @Schema(description = "유저 프로필 이미지 url")
    private String profileImgUrl;
    @Schema(description = "유저 배경 이미지 url")
    private String backgroundImgUrl;
    @Schema(description = "유저 닉네임")
    private String nickname;
    public ArtistSummary(User user){
        this.id = user.getId();
        this.profileImgUrl = user.getProfileImgUrl();
        this.backgroundImgUrl = user.getBackgroundImgUrl();
        this.nickname = user.getNickname();
    }
}
