package plain.spring.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.dto.ArtSummary;

import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "관심 작품")
@AllArgsConstructor
@Builder
public class LikesArt {

    private boolean isRecommended;
    List<ArtSummary> arts;

}
