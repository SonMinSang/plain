package plain.spring.art.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.thumbnailimage.domain.ThumbnailImage;

@Getter
@NoArgsConstructor
@Schema(description = "이미지 url")
@AllArgsConstructor
public class ImageUrl {
    @Schema(description = "이미지 url")
    private String url;
    public ImageUrl(ThumbnailImage thumbnailImage){
        this.url = thumbnailImage.getUrl();
    }
}
