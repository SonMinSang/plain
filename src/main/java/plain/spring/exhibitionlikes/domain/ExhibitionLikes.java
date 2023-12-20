package plain.spring.exhibitionlikes.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.user.domain.User;

@Entity
@Table(name = "exhibition_likes")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExhibitionLikes {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "exhibition_likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;
}

