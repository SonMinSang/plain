package plain.spring.exhibition.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.exhibition.domain.Exhibition;

@Getter
@NoArgsConstructor
@Schema(description = "전시 메인 페이지")
@AllArgsConstructor
public class ExhibitionSummary {
    private Long id;
    private String posterImageUrl;
    private String description;
    private String backgroundColor;
    private int likesCount;
    private boolean isLikes;
    private int commentCount;
    public ExhibitionSummary(Exhibition exhibition){
        this.id = exhibition.getId();
        this.posterImageUrl = exhibition.getPosterImageUrl();
        this.description = exhibition.getDescription();
        this.backgroundColor = exhibition.getBackgroundColor();
        this.likesCount = exhibition.getLikesCount();
        this.commentCount = exhibition.getCommentCount();
        this.isLikes = false;
    }
}
