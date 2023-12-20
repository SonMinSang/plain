package plain.spring.art.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "홈화면 작품 리스트 조회용 (좋아요 여부 포함)")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArtSummaryWithLikes {
    @Schema(description = "작품 id")
    private Long id;
    @Schema(description = "작품 이름")
    private String name;
    @Schema(description = "작품 대표 이미지 url")
    private List<ImageUrl> thumbnailImage;
    @Schema(description = "작가 닉네임")
    private String artistNickname;
    @Schema(description = "작품 카테고리")
    private String category;
    @Schema(description = "좋아요 여부")
    private boolean isLikes;

    public ArtSummaryWithLikes(ArtWithLikes artWithLikes){
        this.id = artWithLikes.getArt().getId();
        this.name = artWithLikes.getArt().getName();
        this.thumbnailImage = artWithLikes.getArt().getThumbNailImageUrls().stream().map(a -> new ImageUrl(a)).collect(Collectors.toList());
        this.artistNickname = artWithLikes.getArt().getArtist().getNickname();
        this.category = artWithLikes.getArt().getCategory();
        this.isLikes = artWithLikes.isLikes();
    }
}
