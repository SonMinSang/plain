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
    private String artistNickname;

    private String profileImageUrl;
    private boolean isFollowing;
    public ArtistFollow(User user, boolean isFollowing){
        this.userId = user.getId();
        this.artistNickname = user.getNickname();
        this.profileImageUrl = user.getProfileImgUrl();
        this.isFollowing = isFollowing;
    }
}
