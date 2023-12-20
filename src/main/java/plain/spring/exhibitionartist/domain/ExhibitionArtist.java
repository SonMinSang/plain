package plain.spring.exhibitionartist.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.user.domain.User;

@Entity
@Table(name = "exhibition_artist")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExhibitionArtist {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "exhibition_artist_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

}
