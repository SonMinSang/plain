package plain.spring.S3.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class File {

    private String filename;

    @Schema(allowableValues = {"product", "artist", "제품 사진", "작가 프로필 및 배경"})
    private String category;
}
