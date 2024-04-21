package plain.spring.follow.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.user.domain.User;

@Entity
@Table(name = "follows",
        uniqueConstraints= {
                @UniqueConstraint(
                        name = "follow_unique",
                        columnNames = {
                                "follow_id",
                                "follower_id"
                        }
                )})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;
}

