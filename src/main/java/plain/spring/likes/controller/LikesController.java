package plain.spring.likes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plain.spring.likes.service.LikesService;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
@Tag(name = "좋아요 관련 api", description = "좋아요, 좋아요 취소")
public class LikesController {

    private final LikesService likesService;

    @Operation(summary = "좋아요",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "artId",
                            required = true, description = "좋아요할 작품 id")
            }
    )
    @PostMapping("/{artId}")
    public ResponseEntity likes(@PathVariable Long artId){
        likesService.likes(artId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "좋아요 취소",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "artId",
                            required = true, description = "좋아요 취소할 작품 id")
            }
    )
    @DeleteMapping("/{artId}")
    public ResponseEntity unlikes(@PathVariable Long artId){
        likesService.unlikes(artId);
        return ResponseEntity.ok().build();
    }
}
