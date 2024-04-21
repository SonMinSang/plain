package plain.spring.usersetting;

import jakarta.persistence.*;
import lombok.*;
import plain.spring.user.domain.User;

@Entity
@Table(name = "user_settings")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSetting {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_setting_id")
    private Long id;

    @OneToOne(mappedBy = "userSetting")
    private User user;
    private boolean activityNotification = false;
    private boolean registerNotification = false;
    private boolean serviceNotification = false;
    @Setter
    private boolean blockRequest = false;


}
