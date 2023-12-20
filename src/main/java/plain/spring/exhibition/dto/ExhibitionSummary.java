package plain.spring.exhibition.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "전시 메인 페이지")
@AllArgsConstructor
public class ExhibitionSummary {
    private Long id;
    private String posterImageUrl;
    private String description;
    private int likesCount;
    private boolean isLiked;
    private int commentCount;
}
