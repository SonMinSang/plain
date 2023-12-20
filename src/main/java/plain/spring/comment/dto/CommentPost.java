package plain.spring.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "댓글 작성")
@AllArgsConstructor
public class CommentPost {
    @Schema(description = "댓글 내용")
    private String content;
}
