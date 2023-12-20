package plain.spring.thumbnailimage.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.domain.Art;

@Entity
@Table(name = "thumbnail_image")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThumbnailImage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "thumbnail_id")
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Art art;
}
