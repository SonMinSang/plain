package plain.spring.art.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.user.domain.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistFollow {
    private Long userId;
    private String nickname;

    private String profileImageUrl;
    private boolean isFollowing;
    public ArtistFollow(User user, boolean isFollowing){
        this.userId = user.getId();
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
        this.isFollowing = isFollowing;
    }
}
