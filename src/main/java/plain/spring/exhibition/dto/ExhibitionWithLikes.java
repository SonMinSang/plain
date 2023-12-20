package plain.spring.exhibition.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.exhibition.domain.Exhibition;

@Getter
@NoArgsConstructor
@Schema(description = "전시 상세 정보")
@AllArgsConstructor
@Builder
public class ExhibitionWithLikes {
    private Exhibition exhibition;
    private boolean isLiked;
}
