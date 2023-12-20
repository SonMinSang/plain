package plain.spring.usertag.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.tag.domain.Tag;
import plain.spring.user.domain.User;

@Entity
@Table(name = "user_tag")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
