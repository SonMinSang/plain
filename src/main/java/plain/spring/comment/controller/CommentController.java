package plain.spring.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plain.spring.comment.dto.CommentDisplay;
import plain.spring.comment.dto.CommentPost;
import plain.spring.comment.service.CommentService;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "댓글 관련 api", description = "댓글 CRUD")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "해당 작품의 모든 댓글 반환",
            parameters = {
                    @Parameter(in = PATH, name = "artId",
                            required = true, description = "작품 Id")
            }
    )
    @GetMapping("/arts/{artId}/comments")
    public ResponseEntity<List<CommentDisplay>> getAllComments(@PathVariable("artId") Long artId){

        return ResponseEntity.ok(commentService.getAllCommentsByArtId(artId));
    }

    @Operation(summary = "해당 작품에 댓글 달기",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "artId",
                            required = true, description = "작품 Id")
            }
    )
    @PostMapping("/arts/{artId}/comments")
    public ResponseEntity<CommentDisplay> postComment(@PathVariable("artId") Long artId, @RequestBody CommentPost commentPost){
        return ResponseEntity.ok(commentService.postComment(artId, commentPost));
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
        return ResponseEntity.ok(commentService.editComment(commentId, commentPost));
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
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
