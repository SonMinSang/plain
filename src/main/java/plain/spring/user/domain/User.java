package plain.spring.user.domain;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.art.domain.Art;
import plain.spring.user.dto.UserInfoEdit;
import plain.spring.userjob.domain.UserJob;
import plain.spring.usertag.domain.UserTag;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "nickname", length = 50)
    private String nickname;
    private String oauthId;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String profileImgUrl;
    private String backgroundImgUrl;
    private String email;
    private String openChatUrl;
    @Builder.Default
    private int followedCount = 0;
    @Builder.Default
    @OneToMany(mappedBy = "artist")
    private List<Art> arts = new ArrayList<>();
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTag> userTags = new ArrayList<>();
    @Setter
    @OneToMany(mappedBy = "user")
    private List<UserJob> userJobs = new ArrayList<>();

    private String deviceToken;
    public void followed(){
        followedCount++;
    }

    public void unfollowed(){
        if (followedCount <= 0){

        }
        followedCount--;
    }
    public void editNickname(String nickname){
        this.nickname = nickname;
    }



    public void editUserInfo(UserInfoEdit userInfoEdit) {
        this.nickname = userInfoEdit.getNickname();
        this.profileImgUrl = userInfoEdit.getProfileImgUrl();
        this.backgroundImgUrl = userInfoEdit.getBackgroundImgUrl();
        this.email = userInfoEdit.getEmail();
        this.openChatUrl = userInfoEdit.getOpenChatUrl();
    }

    public void editDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
