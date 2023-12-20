package plain.spring.exhibition.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.dto.ArtistFollow;
import plain.spring.art.dto.ImageInfo;
import plain.spring.exhibitionart.domain.ExhibitionArt;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@NoArgsConstructor
@Schema(description = "전시내 작품 정보")
@AllArgsConstructor
@Builder
public class ExhibitionArtDetail {

    private Long id;
    private String name;
    private String description;
    private List<String> thumbnailImageUrls;
    private List<ImageInfo> detailImageInfo;
    private ArtistFollow artistFollow;

    public ExhibitionArtDetail(ExhibitionArt exhibitionArt){
        this.id = exhibitionArt.getId();
        this.name = exhibitionArt.getName();
        this.description = exhibitionArt.getDescription();
        this.thumbnailImageUrls = exhibitionArt.getThumbnailImageUrls().stream().map(t -> t.getUrl()).collect(Collectors.toList());
        this.detailImageInfo = exhibitionArt.getDetailImageUrls().stream().map(d -> new ImageInfo(d)).collect(Collectors.toList());
        this.artistFollow = new ArtistFollow(exhibitionArt.getArtist(), false);
    }
    public ExhibitionArtDetail(ExhibitionArt exhibitionArt, Set<Long> userIds){
        this.id = exhibitionArt.getId();
        this.name = exhibitionArt.getName();
        this.description = exhibitionArt.getDescription();
        this.thumbnailImageUrls = exhibitionArt.getThumbnailImageUrls().stream().map(t -> t.getUrl()).collect(Collectors.toList());
        this.detailImageInfo = exhibitionArt.getDetailImageUrls().stream().map(d -> new ImageInfo(d)).collect(Collectors.toList());
        this.artistFollow = new ArtistFollow(exhibitionArt.getArtist(), userIds.contains(exhibitionArt.getArtist()));
    }
}
