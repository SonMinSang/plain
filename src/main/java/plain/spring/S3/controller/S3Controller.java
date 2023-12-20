package plain.spring.S3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import plain.spring.S3.service.S3Service;
import plain.spring.S3.domain.File;
import plain.spring.S3.dto.PresignedUrl;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/s3")
public class S3Controller {
    private final S3Service s3Service;

    @Operation(summary = "하나의 이미지에 대한 presignedUrl 요청", description = "이미지 업로드시 필요한 presignedUrl을 요청한다",
            parameters = {
                    @Parameter(in = HEADER, example = "Bearer accessToken", name = "Authorization", description = "AccessToken", required = true),
            }
    )
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/single")
    public ResponseEntity<PresignedUrl> findPresignedUrlAndImageUrl(@RequestBody File filename){
        return ResponseEntity.ok(s3Service.findPresignedUrlAndImageUrl(filename));
    }

    @Operation(summary = "여러 이미지에 대한 presignedUrl 요청", description = "이미지 업로드시 필요한 presignedUrl을 요청한다",
            parameters = {
                    @Parameter(in = HEADER, example = "Bearer accessToken", name = "Authorization", description = "AccessToken", required = true),
            }
    )
    @PostMapping("/list")
    public ResponseEntity<List<PresignedUrl>> findPresignedUrlsAndImageUrls(@RequestBody List<File> filename){
        return ResponseEntity.ok(s3Service.findPresignedUrlsAndImageUrls(filename));
    }
}
