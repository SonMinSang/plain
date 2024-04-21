package plain.spring.block;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/block")
@Tag(name = "차단 관련 API", description = "차단")
public class BlockController {
    private final BlockService blockService;

    @Operation(summary = "유저 차단",
            parameters = {
                    @Parameter(in = HEADER, example = "Bearer accessToken", name = "Authorization", description = "AccessToken", required = true),
                    @Parameter(in = PATH, example = "1", name = "userId", description = "차단할 유저 ID", required = true),
            }
    )
    @PostMapping("/{userId}")
    public ResponseEntity block(@PathVariable Long userId){
        blockService.block(userId);
        return ResponseEntity.ok().build();
    }

//    @DeleteMapping("/{userId}")
//    public ResponseEntity unblock(@PathVariable Long userId){
//        blockService.unblock(userId);
//        return ResponseEntity.ok().build();
//    }
}
