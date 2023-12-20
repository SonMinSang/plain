package plain.spring.exhibition.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.exhibition.domain.Exhibition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Schema(description = "전시 정보")
@AllArgsConstructor
@Builder
public class ExhibitionInfo {
    private Long id;
    private String name;
    private String englishName;
    private String posterImageUrl;
    private String category;
    private String description;
    private String artCount;
    private String estimatedDuration;
    private List<ExhibitionArtistSummary> artists = new ArrayList<>();

    public ExhibitionInfo(Exhibition exhibition){
        this.id = exhibition.getId();
        this.name = exhibition.getName();
        this.englishName = exhibition.getEnglishName();
        this.posterImageUrl = exhibition.getPosterImageUrl();
        this.description = exhibition.getDescription();
        this.artCount = exhibition.getArtCount();
        this.estimatedDuration = exhibition.getEstimatedDuration();
        this.artists = exhibition.getArtists().stream().map(a -> new ExhibitionArtistSummary(a.getUser())).collect(Collectors.toList());
    }
}
