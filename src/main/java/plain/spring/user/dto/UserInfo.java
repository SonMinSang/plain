package plain.spring.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plain.spring.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Schema(description = "유저 정보")
@AllArgsConstructor
public class UserInfo {

    @Schema(description = "유저 id")
    private Long id;
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

    @Schema(description = "팔로우 여부")
    private boolean isFollowing;

    public UserInfo(User user){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
        this.backgroundImageUrl = user.getBackgroundImageUrl();
        this.tags = user.getUserTags().stream().map(tags -> tags.getTag().getName()).collect(Collectors.toList());
        this.jobs = user.getUserJobs().stream().map(jobs -> jobs.getJob().getName()).collect(Collectors.toList());
        this.email = user.getEmail();
        this.openChatUrl = user.getOpenChatUrl();
        this.isFollowing = false;

    }

    public UserInfo(User user, boolean isFollowing, boolean isBlockRequest){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
        this.backgroundImageUrl = user.getBackgroundImageUrl();
        this.tags = user.getUserTags().stream().map(tags -> tags.getTag().getName()).collect(Collectors.toList());
        this.jobs = user.getUserJobs().stream().map(jobs -> jobs.getJob().getName()).collect(Collectors.toList());
        if (!isBlockRequest) {
            this.email = user.getEmail();
            this.openChatUrl = user.getOpenChatUrl();
        }

        this.isFollowing = isFollowing;

    }

}
