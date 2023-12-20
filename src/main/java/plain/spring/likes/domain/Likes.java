package plain.spring.likes.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import plain.spring.art.domain.Art;
import plain.spring.user.domain.User;

@Entity
@Table(name = "likes")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Likes {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Art art;
}
