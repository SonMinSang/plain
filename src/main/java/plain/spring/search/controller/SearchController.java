package plain.spring.search.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plain.spring.art.dto.ArtSummary;
import plain.spring.art.service.ArtService;
import plain.spring.search.service.SearchService;
import plain.spring.user.dto.ArtistSummary;
import plain.spring.user.service.UserService;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
@Tag(name = "검색 관련 API", description = "작품 검색, 작가 검색")
public class SearchController {

    private final ArtService artService;
    private final UserService userService;

    private final SearchService searchService;
    @Operation(summary = "작품 검색 조회",
            parameters = {
                    @Parameter(in = QUERY, name = "query",
                            required = false, description = "검색어"),
                    @Parameter(in = QUERY, name = "category",
                            required = false, description = "카테고리"),
                    @Parameter(in = QUERY, name = "pageNo",
                            required = false, description = "페이지 번호")

            }
    )
    @GetMapping("/arts")
    public ResponseEntity<Slice<ArtSummary>> getSearchArts(@RequestParam(required = false) String query, @RequestParam(required = false) String category, @RequestParam(required = false, defaultValue = "0") int pageNo){
        Slice<ArtSummary> results = artService.getArtsByQueryAndCategory(query, category, pageNo);
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "작가 검색 조회",
            parameters = {
                    @Parameter(in = QUERY, name = "query",
                            required = false, description = "검색어"),
                    @Parameter(in = QUERY, name = "pageNo",
                            required = false, description = "페이지 번호")

            }
    )
    @GetMapping("/artists")
    public ResponseEntity<Slice<ArtistSummary>> getSearchArtists(@RequestParam(required = false) String query, @RequestParam(required = false) String category, @RequestParam(required = false, defaultValue = "0") int pageNo){
        Slice<ArtistSummary> results = userService.getArtistsByQueryAndCategory(query, category, pageNo);
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "최근 검색어 조회",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            }
    )
    @GetMapping("/recent-search")
    public ResponseEntity<List<String>> getRecentSearchQuery(){
        List<String> results = searchService.getAllSearchQuery();
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "최근 검색어 선택 삭제",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = QUERY, name = "query",
                            required = false, description = "검색어"),
            }
    )
    @DeleteMapping("/recent-search/{query}")
    public ResponseEntity deleteRecentSearchQuery(@PathVariable(name = "query") String query){
        searchService.deleteSearchQuery(query);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "최근 검색어 전체 삭제",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            }
    )
    @DeleteMapping("/recent-search")
    public ResponseEntity deleteAllRecentSearchQuery(){
        searchService.deleteAllSearchQuery();
        return ResponseEntity.ok().build();
    }
}
