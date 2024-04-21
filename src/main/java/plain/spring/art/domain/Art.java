package plain.spring.art.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import plain.spring.art.dto.ArtPost;
import plain.spring.arttag.domain.ArtTag;
import plain.spring.commons.util.BaseTimeEntity;
import plain.spring.image.domain.ArtImage;
import plain.spring.likes.domain.Likes;
import plain.spring.thumbnailimage.domain.ThumbnailImage;
import plain.spring.user.domain.User;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "art")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Art extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "art_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User artist;
    private String name;

    private boolean forSale;
    private long price;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;
    @Builder.Default
    private int likesCount = 0;

    @Setter
    @Builder.Default
    @OneToMany(mappedBy = "art")
    private List<ThumbnailImage> thumbnailImageUrls = new ArrayList<>();

    @Setter
    @Builder.Default
    @OneToMany(mappedBy = "art")
    private List<ArtTag> artTagList = new ArrayList<>();

    @Setter
    @Builder.Default
    @OneToMany(mappedBy = "art")
    private List<ArtImage> artImageUrls = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "art")
    private List<Likes> likes = new ArrayList<>();

    @Version
    private Long version;

    public void update(ArtPost artPost){
        this.name = artPost.getName();
        this.price = artPost.getPrice();
        this.forSale = artPost.isForSale();
        this.category = artPost.getCategory();
        this.description = artPost.getDescription();
    }
}
