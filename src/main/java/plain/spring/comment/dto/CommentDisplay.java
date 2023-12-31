package plain.spring.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.comment.domain.Comment;
import plain.spring.exhibitioncomment.domain.ExhibitionComment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Schema(description = "댓글 조회")
@AllArgsConstructor
public class CommentDisplay {
    @Schema(description = "댓글 id")
    private Long id;
    @Schema(description = "유저 id")
    private Long userId;
    @Schema(description = "유저 프로필 이미지 url")
    private String profileImageUrl;
    @Schema(description = "유저 닉네임")
    private String nickname;

    @Schema(description = "댓글 내용")
    private String content;

    @Schema(description = "현재는 댓글 작성 시간 반환 추후 기간 계산 후 String으로 반환 예정")
    private LocalDateTime createdAt;

    public CommentDisplay(Comment comment){
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.profileImageUrl = comment.getUser().getProfileImageUrl();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

    public CommentDisplay(ExhibitionComment comment){
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.profileImageUrl = comment.getUser().getProfileImageUrl();
        this.nickname = comment.getUser().getNickname();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }
}
