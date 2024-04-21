package plain.spring.report;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "알림")
@AllArgsConstructor
@Builder
public class ReportInput {
    private Long userId;
    private ReportProblem reportProblem;
}
