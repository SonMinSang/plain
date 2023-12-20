package plain.spring.userjob.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.job.domain.Job;
import plain.spring.user.domain.User;

@Entity
@Table(name = "user_job")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserJob {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_job_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;
}

