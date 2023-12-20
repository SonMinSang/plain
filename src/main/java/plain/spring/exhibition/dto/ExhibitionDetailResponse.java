package plain.spring.exhibition.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Schema(description = "전시 상세 정보")
@AllArgsConstructor
@Builder
public class ExhibitionDetailResponse {
    private Long id;
    private String name;
    private String englishName;
    private String posterImageUrl;
    private String category;
    private String description;
    private String artCount;
    private String estimatedDuration;
    private List<ExhibitionArtistSummary> artists = new ArrayList<>();
    private List<ExhibitionArtDetail> arts = new ArrayList<>();
    private int likesCount;
    private boolean isLiked;
    private int commentCount;

    public ExhibitionDetailResponse(ExhibitionWithLikes exhibition, Set<Long> userLists){
        this.id = exhibition.getExhibition().getId();
        this.name = exhibition.getExhibition().getName();
        this.englishName = exhibition.getExhibition().getEnglishName();
        this.posterImageUrl = exhibition.getExhibition().getPosterImageUrl();
        this.description = exhibition.getExhibition().getDescription();
        this.artCount = exhibition.getExhibition().getArtCount();
        this.estimatedDuration = exhibition.getExhibition().getEstimatedDuration();
        this.artists = exhibition.getExhibition().getArtists().stream().map(a -> new ExhibitionArtistSummary(a.getUser())).collect(Collectors.toList());
        this.likesCount = exhibition.getExhibition().getLikesCount();
        this.commentCount = exhibition.getExhibition().getCommentCount();
        this.isLiked = exhibition.isLiked();
        this.arts = exhibition.getExhibition().getArts().stream().map(a -> new ExhibitionArtDetail(a, userLists)).collect(Collectors.toList());
    }
    public ExhibitionDetailResponse(ExhibitionWithLikes exhibition){
        this.id = exhibition.getExhibition().getId();
        this.name = exhibition.getExhibition().getName();
        this.englishName = exhibition.getExhibition().getEnglishName();
        this.posterImageUrl = exhibition.getExhibition().getPosterImageUrl();
        this.description = exhibition.getExhibition().getDescription();
        this.artCount = exhibition.getExhibition().getArtCount();
        this.estimatedDuration = exhibition.getExhibition().getEstimatedDuration();
        this.artists = exhibition.getExhibition().getArtists().stream().map(a -> new ExhibitionArtistSummary(a.getUser())).collect(Collectors.toList());
        this.likesCount = exhibition.getExhibition().getLikesCount();
        this.commentCount = exhibition.getExhibition().getCommentCount();
        this.isLiked = exhibition.isLiked();
        this.arts = exhibition.getExhibition().getArts().stream().map(a -> new ExhibitionArtDetail(a)).collect(Collectors.toList());
    }
}
