package plain.spring.exhibition.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.exhibitionart.domain.ExhibitionArt;
import plain.spring.exhibitionartist.domain.ExhibitionArtist;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exhibition")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exhibition {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "exhibition_id")
    private Long id;
    private String name;
    private String englishName;
    private String posterImageUrl;
    private String category;

    @Builder.Default
    private String description = "";
    private String backgroundColor;

    @Builder.Default
    @OneToMany(mappedBy = "exhibition")
    private List<ExhibitionArt> arts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "exhibition")
    private List<ExhibitionArtist> artists = new ArrayList<>();
    @Builder.Default
    private int likesCount = 0;
    @Builder.Default
    private int commentCount = 0;
    private String artCount;
    private String estimatedDuration;
    private String status;

    public void likes(){
        likesCount++;
    }
    public void unlikes(){
        if (likesCount <= 0){

        }
        likesCount--;
    }
    public void postComment(){
        commentCount++;
    }
    public void deleteComment(){
        if (commentCount <= 0){

        }
        commentCount--;
    }
}
