package plain.spring.art.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.domain.Art;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArtWithLikes {
    private Art art;
    private boolean isLikes;

}