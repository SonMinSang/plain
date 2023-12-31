package plain.spring.art.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plain.spring.art.dto.ArtDetail;
import plain.spring.art.dto.ArtPost;
import plain.spring.art.dto.ArtSummary;
import plain.spring.art.dto.ArtSummaryWithLikes;
import plain.spring.art.service.ArtService;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/arts")
@Tag(name = "작품 관련 API", description = "홈 화면")
public class ArtController {
    private final ArtService artService;

    @Operation(summary = "홈 화면 작품 리스트", description = "홈 화면 작품 리스트 반환 로그인 상태면 jwt token과 같이 요청하여 좋아요 여부 반환 아닐 경우 token 없이 요청.",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = false, description = "Bearer {jwt token}"),
                    @Parameter(in = QUERY, name = "category",
                            required = false, description = "안 넣을 시 전체 조회 카데고리를 한글 그대로 요청"),
                    @Parameter(in = QUERY, name = "lastId",
                            required = false, description = "무한 스크롤 시 새로운 페이지를 받기 위해 이전 결과값의 마지막 id 값을 요청, 첫 요청 시 id 값 없이 요청 ")

            }
    )
    @GetMapping
    public ResponseEntity<Slice<ArtSummaryWithLikes>> getHomeArts(@RequestParam(required = false, value = "category") String category, @RequestParam(required = false, value = "lastId") Long id){
        Slice<ArtSummaryWithLikes> results = artService.getHomeArtsByCategory(category, id);
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "작품 업로드",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            }
    )
    @PostMapping
    public ResponseEntity<ArtSummary> uploadArt(@RequestBody ArtPost artPost){
        ArtSummary result = artService.uploadArt(artPost);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "작품 수정", description = "작품 썸네일 이미지 목록과 작품 상세 이미지 목록은 수정 사항 없을시 비어놓고 요청",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "artId",
                            required = true, description = "작품 ID"),

            }
    )
    @PutMapping("/{artId}")
    public ResponseEntity<ArtSummary> updateArt(@PathVariable("artId") Long artId, @RequestBody ArtPost artPost){
        ArtSummary result = artService.updateArt(artId, artPost);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "작품 상세 페이지", description = "작품 상세 페이지 반환 로그인 상태면 jwt token과 같이 요청하여 좋아요, 팔로우 여부 반환 아닐 경우 token 없이 요청.",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = false, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "artId",
                            required = true, description = "원하는 작품의 id")

            }
    )
    @GetMapping("/{artId}")
    public ResponseEntity<ArtDetail> getArtDetail(@PathVariable("artId") Long artId){
        ArtDetail result = artService.getArtById(artId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "작품 상세 페이지 작가의 다른 작품",
            parameters = {
                    @Parameter(in = PATH, name = "artistId",
                            required = false, description = "현재 보고 있는 작품의 작가 id"),
                    @Parameter(in = PATH, name = "artId",
                            required = true, description = "현재 보고 있는 작품 id")

            }
    )

    @GetMapping("/{artId}/artist/{artistId}/others")
    public ResponseEntity<List<ArtSummary>> getArtistOtherArts(@PathVariable("artistId") Long artistId, @PathVariable("artId") Long artId){
        List<ArtSummary> result =  artService.getArtistsOtherArts(artistId, artId);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "작품 상세페이지 관련된 작품",
            parameters = {
                    @Parameter(in = PATH, name = "artId",
                            required = true, description = "현재 보고 있는 작품 id")
            }
    )
    @GetMapping("/{artId}/recommends")
    public ResponseEntity<List<ArtSummary>> getSimilarArts(@RequestBody List<String> tags, @PathVariable(value = "artId") Long artId){
        List<ArtSummary> result = artService.getSimilarOtherArts(tags, artId);
        return ResponseEntity.ok(result);
    }
}
