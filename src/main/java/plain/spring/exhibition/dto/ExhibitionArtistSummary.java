package plain.spring.exhibition.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.user.domain.User;

@Getter
@NoArgsConstructor
@Schema(description = "전시내 작품 정보")
@AllArgsConstructor
@Builder
public class ExhibitionArtistSummary {
    @Schema(description = "유저 id")
    private Long id;
    @Schema(description = "유저 프로필 이미지 url")
    private String profileImageUrl;
    @Schema(description = "유저 닉네임")
    private String nickname;
    private String job;
    public ExhibitionArtistSummary(User user){
        this.id = user.getId();
        this.profileImageUrl = user.getProfileImageUrl();
        this.nickname = user.getNickname();
        if (user.getUserJobs() == null || user.getUserJobs().isEmpty())
            this.job = "";
        else {
            this.job = user.getUserJobs().get(0).getJob().getName();
        }
    }

}
