package plain.spring.art.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArtPost {

    private String name;
    private long price;
    private String category;
    private String description;
    private List<String> thumbNailImageUrls = new ArrayList<>();
    private List<String> artImageUrls = new ArrayList<>();
    private List<String> artTagList = new ArrayList<>();

}
