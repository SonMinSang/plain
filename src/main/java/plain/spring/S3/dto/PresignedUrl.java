package plain.spring.S3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class PresignedUrl {
    private String presignedUrl;
    private String imageUrl;
}
