package plain.spring.exhibition.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plain.spring.comment.dto.CommentDisplay;
import plain.spring.comment.dto.CommentPost;
import plain.spring.exhibition.dto.ExhibitionDetailResponse;
import plain.spring.exhibition.dto.ExhibitionInfo;
import plain.spring.exhibition.dto.ExhibitionSummary;
import plain.spring.exhibition.service.ExhibitionService;
import plain.spring.exhibitioncomment.service.ExhibitionCommentService;
import plain.spring.exhibitionlikes.service.ExhibitionLikesService;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exhibitions")
@Tag(name = "전시 관련 api", description = "댓글, 좋아요 포함")
public class ExhibitionController {

    private final ExhibitionService exhibitionService;
    private final ExhibitionCommentService exhibitionCommentService;

    private final ExhibitionLikesService exhibitionLikesService;

    @Operation(summary = "관람 가능 전시 목록",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = false, description = "Bearer {jwt token}")
            }
    )
    @GetMapping
    public ResponseEntity<List<ExhibitionSummary>> getAllAvailableExhibitions(){
        List<ExhibitionSummary> results = exhibitionService.getAllAvailableExhibitions();
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "전시 상세 조회",
            parameters = {
                    @Parameter(in = PATH, name = "exhibitionId",
                            required = true, description = "전시회 Id"),
                    @Parameter(in = HEADER, name = "Authorization",
                            required = false, description = "Bearer {jwt token}")
            }
    )
    @GetMapping("/{exhibitionId}")
    public ResponseEntity<ExhibitionDetailResponse> getExhibitionDetail(@PathVariable("exhibitionId") Long exhibitionId){
        ExhibitionDetailResponse result = exhibitionService.getExhibitionDetail(exhibitionId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "전시 정보 조회",
            parameters = {
                    @Parameter(in = PATH, name = "exhibitionId",
                            required = true, description = "전시회 Id")
            }
    )
    @GetMapping("/{exhibitionId}/info")
    public ResponseEntity<ExhibitionInfo> getExhibitionInfo(@PathVariable("exhibitionId") Long exhibitionId){
        ExhibitionInfo result = exhibitionService.getExhibitionInfo(exhibitionId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "해당 전시회의 댓글 조회",
            parameters = {
                    @Parameter(in = PATH, name = "exhibitionId",
                            required = true, description = "전시회 Id")
            }
    )
    @GetMapping("/{exhibitionId}/comments")
    public ResponseEntity<List<CommentDisplay>> getAllComments(@PathVariable("exhibitionId") Long exhibitionId){

        return ResponseEntity.ok(exhibitionCommentService.getAllCommentsByExhibitionId(exhibitionId));
    }

    @Operation(summary = "해당 전시회에 댓글 달기",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "artId",
                            required = true, description = "전시회 Id")
            }
    )
    @PostMapping("/{exhibitionId}/comments")
    public ResponseEntity<CommentDisplay> postComment(@PathVariable("exhibitionId") Long exhibitionId, @RequestBody CommentPost commentPost){
        return ResponseEntity.ok(exhibitionCommentService.postComment(exhibitionId, commentPost));
    }

    @Operation(summary = "댓글 수정",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "commentId",
                            required = true, description = "댓글 Id")
            }
    )
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentDisplay> editComment(@PathVariable("commentId") Long commentId, @RequestBody CommentPost commentPost){
        return ResponseEntity.ok(exhibitionCommentService.editComment(commentId, commentPost));
    }

    @Operation(summary = "댓글 삭제",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "commentId",
                            required = true, description = "댓글 Id")
            }
    )
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity deleteComments(@PathVariable("commentId") Long commentId){

        exhibitionCommentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "좋아요",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "exhibitionId",
                            required = true, description = "좋아요할 전시 id")
            }
    )
    @PostMapping("/{exhibitionId}/likes")
    public ResponseEntity likes(@PathVariable Long exhibitionId){
        exhibitionLikesService.likes(exhibitionId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "좋아요 취소",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "exhibitionId",
                            required = true, description = "좋아요 취소할 전시 id")
            }
    )
    @DeleteMapping("/{exhibitionId}/unlike")
    public ResponseEntity unlikes(@PathVariable Long exhibitionId){
        exhibitionLikesService.unlikes(exhibitionId);
        return ResponseEntity.ok().build();
    }

}
