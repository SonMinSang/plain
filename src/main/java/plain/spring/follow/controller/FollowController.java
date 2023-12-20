package plain.spring.follow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plain.spring.follow.service.FollowService;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
@Tag(name = "팔로우 관련 api", description = "팔로우, 팔로우 취소")
public class FollowController {

    private final FollowService followService;

    @Operation(summary = "팔로우",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "userId",
                            required = true, description = "팔로우할 user id")
            }
    )
    @PostMapping("/{userId}")
    public ResponseEntity follow(@PathVariable Long userId){
        followService.follow(userId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "팔로우 취소",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "userId",
                            required = true, description = "팔로우 취소할 user id")
            }
    )
    @DeleteMapping("/{userId}")
    public ResponseEntity unfollow(@PathVariable Long userId){
        followService.unfollow(userId);
        return ResponseEntity.ok().build();
    }
}
