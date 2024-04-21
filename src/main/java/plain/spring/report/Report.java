package plain.spring.report;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.user.domain.User;

@Entity
@Table(name = "report")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "reported_id")
    private User reported;

    @Enumerated(EnumType.STRING)
    private ReportProblem reportProblem;
}
