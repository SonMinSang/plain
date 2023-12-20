package plain.spring.arttag.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.domain.Art;
import plain.spring.tag.domain.Tag;

@Entity
@Table(name = "art_tag")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtTag {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "art_tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Art art;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}

