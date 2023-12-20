package plain.spring.exhibitioncomment.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.commons.util.BaseTimeEntity;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.user.domain.User;

@Entity
@Table(name = "exhibition_comment")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExhibitionComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "exhibition_comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    private String content;

    public void edit(String content){
        this.content = content;
    }
}

