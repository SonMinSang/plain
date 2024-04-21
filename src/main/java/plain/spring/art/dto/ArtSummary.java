package plain.spring.art.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.domain.Art;


@Schema(description = "작품 리스트 조회용")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArtSummary {
    @Schema(description = "작품 id")
    private Long id;
    @Schema(description = "작품 이름")
    private String name;
    @Schema(description = "작품 대표 이미지 url")
    private String thumbnailImageUrl;
    @Schema(description = "작가 닉네임")
    private String nickname;

    public ArtSummary(Art art){
        this.id = art.getId();
        this.name = art.getName();
        this.thumbnailImageUrl = art.getThumbnailImageUrls().get(0).getUrl();
        this.nickname = art.getArtist().getNickname();
    }

    public ArtSummary(Art art, String nickname){
        this.id = art.getId();
        this.name = art.getName();
        this.thumbnailImageUrl = art.getThumbnailImageUrls().get(0).getUrl();
        this.nickname = nickname;
    }
}
