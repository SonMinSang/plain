package plain.spring.image.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.art.domain.Art;

@Entity
@Table(name = "art_image")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtImage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "image_id")
    private Long id;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Art art;

    private int width;
    private int height;
}
