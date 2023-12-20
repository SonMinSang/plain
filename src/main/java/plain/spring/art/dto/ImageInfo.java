package plain.spring.art.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.ExhibitionArtImage.domain.ExhibitionArtImage;
import plain.spring.image.domain.ArtImage;

@Getter
@NoArgsConstructor
@Schema(description = "이미지 정보")
@AllArgsConstructor
public class ImageInfo {
    @Schema(description = "이미지 url")
    private String url;
    private int width;
    private int height;
    public ImageInfo(ArtImage artImage){

        this.url = artImage.getUrl();
        this.width = artImage.getWidth();
        this.height = artImage.getHeight();
    }
    public ImageInfo(ExhibitionArtImage exhibitionArtImage){
        this.url = exhibitionArtImage.getUrl();
        this.width = exhibitionArtImage.getWidth();
        this.height = exhibitionArtImage.getHeight();
    }
}
