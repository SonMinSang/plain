package plain.spring.exhibitionart.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.ExhibitionArtImage.domain.ExhibitionArtImage;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.exhibitionartthumbnailimage.domain.ExhibitionArtThumbnailImage;
import plain.spring.user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exhibition_art")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExhibitionArt {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "exhibition_art_id")
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    @Builder.Default
    private String description = "";

    @Builder.Default
    @OneToMany(mappedBy = "exhibitionArt")
    private List<ExhibitionArtThumbnailImage> thumbnailImageUrls = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "exhibitionArt")
    private List<ExhibitionArtImage> detailImageUrls = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibition_id")
    private Exhibition exhibition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User artist;
}
