package plain.spring.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.domain.Art;

@Getter
@NoArgsConstructor
@Schema(description = "작가 페이지 작품")
@AllArgsConstructor
public class ArtistPageArt {
    @Schema(description = "작품 Id")
    private Long id;
    @Schema(description = "작품 이름")
    private String name;
    @Schema(description = "작품 대표 이미지 url")
    private String thumbnailImgUrl;
    @Schema(description = "작가 이름")
    private String artistName;

    public ArtistPageArt(Art art){
        this.id = art.getId();
        this.name = art.getName();
        this.thumbnailImgUrl = art.getThumbNailImageUrls().get(0).getUrl();
    }
    public ArtistPageArt(Art art, String artistName){
        this.id = art.getId();
        this.name = art.getName();
        this.thumbnailImgUrl = art.getThumbNailImageUrls().get(0).getUrl();
        this.artistName = artistName;

    }
}
