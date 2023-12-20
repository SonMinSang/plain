package plain.spring.ExhibitionArtImage.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.exhibitionart.domain.ExhibitionArt;

@Entity
@Table(name = "exhibition_art_image")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExhibitionArtImage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "exhibition_art_image_id")
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_art_id")
    private ExhibitionArt exhibitionArt;

    private int width;

    private int height;
}

