package plain.spring.report;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.domain.Art;
import plain.spring.user.domain.User;

@Entity
@Table(name = "art_report")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtReport {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "art_report_id")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Art art;

    @Enumerated(EnumType.STRING)
    private ArtReportProblem artReportProblem;
}
