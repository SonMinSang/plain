package plain.spring.policy.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import plain.spring.user.domain.User;


@Entity
@Table(name = "policy")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

    @Id
    @GeneratedValue
    @Column(name = "conference_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private boolean termsOfService;

    @NotNull
    private boolean useOfPersonalInfo;

    @NotNull
    private boolean receiveMarketingInfo;
}
