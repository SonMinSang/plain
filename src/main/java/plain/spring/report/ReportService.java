package plain.spring.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import plain.spring.art.domain.Art;
import plain.spring.art.repository.ArtRepository;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.exception.ErrorCode;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final ArtReportRepository artReportRepository;
    private final UserRepository userRepository;
    private final ArtRepository artRepository;

    public void report(ReportInput reportInput){
        String id = SecurityUtil.getId().orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User reporter = userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User reported = userRepository.findById(reportInput.getUserId()).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Report report = Report.builder()
                .reporter(reporter)
                .reported(reported)
                .reportProblem(reportInput.getReportProblem())
                .build();
        reportRepository.save(report);
    }

    public void reportArts(ArtReportInput artReportInput){
        String id = SecurityUtil.getId().orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User reporter = userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Art art = artRepository.findById(artReportInput.getArtId()).orElseThrow(() -> new CustomException(ErrorCode.ART_NOT_FOUND));
        ArtReport report = ArtReport.builder()
                .reporter(reporter)
                .art(art)
                .artReportProblem(artReportInput.getArtReportProblem())
                .build();
        artReportRepository.save(report);
    }
}
