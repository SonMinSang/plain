package plain.spring.art.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.domain.Art;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArtWithFollowAndLikes {
    private Art art;
    private boolean isLiked;
    private boolean isFollowing;

}
