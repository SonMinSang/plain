package plain.spring.S3.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import plain.spring.S3.domain.File;
import plain.spring.S3.dto.PresignedUrl;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static plain.spring.commons.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 s3Client;
    private final UserRepository userRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.cdn}")
    private String cdn;

    public List<PresignedUrl> findPresignedUrlsAndImageUrls(List<File> files){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(NOT_MEMBER));
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        List<PresignedUrl> presignedUrls = new ArrayList<>();
        for (File file : files){
            String filename = createFileName(file.getFilename());
            String presignedUrl = createPresignedUrl(file.getCategory(), filename);
            presignedUrls.add(PresignedUrl.builder()
                            .presignedUrl(presignedUrl)
                            .imageUrl(cdn.concat(filename))
                            .build());
        }
        return presignedUrls;
    }

    public PresignedUrl findPresignedUrlAndImageUrl(File file){
        String userId = SecurityUtil.getId().orElseThrow(() -> new CustomException(NOT_MEMBER));
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        String filename = createFileName(file.getFilename());
        String presignedUrl = createPresignedUrl(file.getCategory(), filename);
        return PresignedUrl.builder()
                    .presignedUrl(presignedUrl)
                    .imageUrl(cdn.concat(filename))
                    .build();
    }

    public String createPresignedUrl(String category, String filename) {
        Date expiration = new Date(System.currentTimeMillis() + 3600000); // 1시간 동안 유효한 URL 생성

        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket,"/".concat(category).concat("/").concat(filename))
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration);

        URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    private String createFileName(String fileName) {

        return UUID.randomUUID().toString().concat(fileName);
    }

}
