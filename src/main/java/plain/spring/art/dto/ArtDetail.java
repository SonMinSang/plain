package plain.spring.art.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.domain.Art;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArtDetail {
    private Long id;
    private String name;
    private String description;

    private String category;
    private long price;
    private boolean forSale;
    private List<String> thumbnailImageUrls = new ArrayList<>();
    private List<String> artImageUrls = new ArrayList<>();
    private List<String> artTagList = new ArrayList<>();
    private boolean isLikes;
    private ArtistFollow artist;
    private LocalDateTime createdAt;

    public ArtDetail(ArtWithFollowAndLikes art){
        this.id = art.getArt().getId();
        this.name = art.getArt().getName();
        this.description = art.getArt().getDescription();
        this.category = art.getArt().getCategory();
        this.price = art.getArt().getPrice();
        this.forSale = art.getArt().isForSale();
        this.thumbnailImageUrls = art.getArt().getThumbnailImageUrls().stream().map(t -> t.getUrl()).collect(Collectors.toList());
        this.artImageUrls = art.getArt().getArtImageUrls().stream().map(i -> i.getUrl()).collect(Collectors.toList());
        this.artTagList = art.getArt().getArtTagList().stream().map(t -> t.getTag().getName()).collect(Collectors.toList());
        this.isLikes = art.isLikes();
        this.createdAt = art.getArt().getCreatedAt();
        this.artist = new ArtistFollow(art.getArt().getArtist(), art.isFollowing());

    }
}
