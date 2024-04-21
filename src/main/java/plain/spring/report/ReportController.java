package plain.spring.report;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
@Tag(name = "신고 관련 API", description = "신고")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "유저 신고",
            parameters = {
                    @Parameter(in = HEADER, example = "Bearer accessToken", name = "Authorization", description = "AccessToken", required = true),
            }
    )
    @PostMapping
    public ResponseEntity report(@RequestBody ReportInput reportInput){
        reportService.report(reportInput);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "작품 신고",
            parameters = {
                    @Parameter(in = HEADER, example = "Bearer accessToken", name = "Authorization", description = "AccessToken", required = true),
            }
    )
    @PostMapping("/arts")
    public ResponseEntity reportArts(@RequestBody ArtReportInput artReportInput){
        reportService.reportArts(artReportInput);
        return ResponseEntity.ok().build();
    }
}
