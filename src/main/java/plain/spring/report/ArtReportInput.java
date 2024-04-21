package plain.spring.report;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "작품 신고")
@AllArgsConstructor
@Builder
public class ArtReportInput {
    private Long artId;
    private ArtReportProblem artReportProblem;
}
