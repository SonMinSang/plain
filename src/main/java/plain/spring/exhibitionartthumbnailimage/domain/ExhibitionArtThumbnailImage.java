package plain.spring.exhibitionartthumbnailimage.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.exhibitionart.domain.ExhibitionArt;

@Entity
@Table(name = "exhibition_art_thumbnail")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExhibitionArtThumbnailImage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "exhibition_art_thumbnail_id")
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_art_id")
    private ExhibitionArt exhibitionArt;
}

