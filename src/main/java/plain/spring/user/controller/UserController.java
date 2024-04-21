package plain.spring.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plain.spring.art.dto.ArtSummary;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.exhibition.dto.ExhibitionSummary;
import plain.spring.notification.dto.DeviceToken;
import plain.spring.user.dto.*;
import plain.spring.user.repository.UserRepository;
import plain.spring.user.service.UserService;
import plain.spring.usersetting.UserSettingDTO;
import plain.spring.usersetting.UserSettingService;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;
import static plain.spring.commons.exception.ErrorCode.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "유저 관련 API", description = "작가 페이지 및 자신 정보 조회")
public class UserController {
    private final UserRepository userRepository;
    private final UserSettingService userSettingService;
    private final UserService userService;

    @Operation(summary = "닉네임 중복 체크", description = "중복된 닉네임이 존재할 시 true, 닉네임 사용 가능시 false 반환",
            parameters = {
                    @Parameter(in = PATH, name = "nickname",
                            required = true, description = "체크할 닉네임")
            })
    @GetMapping("/check-duplicate-nickname/{nickname}")
    public ResponseEntity<Boolean> checkDuplicateNickname(@PathVariable("nickname") String nickname){
        if (userRepository.existsOneByNickname(nickname))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.ok(false);
    }

    @Operation(summary = "계정 삭제",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            })
    @DeleteMapping("/me")
    public ResponseEntity deleteMyUser(){
        userService.deleteUser();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "프로필 편집",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            })
    @PutMapping("/me/info")
    public ResponseEntity<UserInfo> editUserInfo(@RequestBody UserInfoEdit userInfoEdit){
        UserInfo result = userService.updateUserInfo(userInfoEdit);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Device Token 변경",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            })
    @PutMapping("/me/device-token")
    public ResponseEntity updateDeviceToken(@RequestBody DeviceToken deviceToken){
        userService.updateDeviceToken(deviceToken);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "유저 세팅 조회",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            })
    @GetMapping("/me/setting")
    public ResponseEntity<UserSettingDTO> findUserSetting(){
        UserSettingDTO result = userSettingService.findMyUserSetting();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "유저 세팅 변경",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            })
    @PutMapping("/me/setting")
    public ResponseEntity<UserSettingDTO> editUserSetting(@RequestBody SettingEdit settingEdit){
        UserSettingDTO result = userSettingService.editMyUserSetting(settingEdit);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "닉네임 변경", description = "변경 순간 중복된 닉네임이 존재할 시 에러 반환 (아직 에러 처리는 안됨)",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            })
    @PutMapping("/me/nickname")
    public ResponseEntity<UserInfo> editNickname(@RequestBody Nickname nickname) throws Exception {
        UserInfo result = userService.editNickname(nickname);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "초기 로그인시 유저 정보 반환",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            })
    @GetMapping("/me/info")
    public ResponseEntity<UserInfo> getMyUserInfo(){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow(() -> new CustomException(NOT_VALID_HEADER)));
        UserInfo result = userService.getUserInfo(userId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "작가 페이지 유저 정보 반환",
            parameters = {
                    @Parameter(in = PATH, name = "userId",
                            required = true, description = "유저 Id")
            })
    @GetMapping("/{userId}/info")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable Long userId){
        UserInfo result = userService.getUserInfo(userId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "작가 페이지 팔로우한 유저 목록 반환",
            parameters = {
                    @Parameter(in = PATH, name = "userId",
                            required = true, description = "유저 Id")
            })
    @GetMapping("/{userId}/follow")
    public ResponseEntity<List<ArtistSummary>> getUserFollow(@PathVariable Long userId){
        List<ArtistSummary> results = userService.getUserFollow(userId);
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "작가 팔로우 목록 없을 시 추천 유저 목록 반환",
            parameters = {
                    @Parameter(in = PATH, name = "userId",
                            required = true, description = "유저 Id")
            })
    @GetMapping("/me/follow/recommendation")
    public ResponseEntity<List<ArtistSummary>> getFollowRecommendation(){
        List<ArtistSummary> results = userService.getFollowRecommendation();
        return ResponseEntity.ok(results);
    }

    @Operation(summary = "좋아요 누른 작품 리스트 반환",
            parameters = {
                    @Parameter(in = PATH, name = "userId",
                            required = true, description = "유저 Id")
            })
    @GetMapping("/{userId}/likes")
    public ResponseEntity<List<ArtSummary>> getUserLikes(@PathVariable Long userId){
        List<ArtSummary> result = userService.getUserLikes(userId);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "좋아요 누른 작품 리스트 없을 시 추천 작품 반환",
            parameters = {
                    @Parameter(in = PATH, name = "userId",
                            required = true, description = "유저 Id")
            })
    @GetMapping("/me/likes/recommendation")
    public ResponseEntity<List<ArtSummary>> getLikesRecommendation(){
        List<ArtSummary> result = userService.getLikesRecommendation();
        return ResponseEntity.ok(result);
    }
    @Operation(summary = "작업한 작품 리스트 반환",
            parameters = {
                    @Parameter(in = PATH, name = "userId",
                            required = true, description = "유저 Id")
            })
    @GetMapping("/{userId}/arts")
    public ResponseEntity<List<ArtSummary>> getUserArts(@PathVariable Long userId){
        List<ArtSummary> result = userService.getUserArts(userId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "참여한 전시 리스트 반환",
            parameters = {
                    @Parameter(in = PATH, name = "userId",
                            required = true, description = "유저 Id")
            })
    @GetMapping("/{userId}/exhibitions")
    public ResponseEntity<List<ExhibitionSummary>> getUserExhibitions(@PathVariable Long userId){
        List<ExhibitionSummary> result = userService.getUserExhibitions(userId);
        return ResponseEntity.ok(result);
    }
}
