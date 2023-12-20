package plain.spring.notification.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.art.domain.Art;
import plain.spring.commons.util.BaseTimeEntity;
import plain.spring.user.domain.User;

@Entity
@Table(name = "notification")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "notification_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Builder.Default
    private NotificationStatus status = NotificationStatus.UNREAD;

    private Long receiverId;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "art_id")
    private Art art;

    public void read(){
        this.status = NotificationStatus.READ;
    }
    public String getBody() {
        switch(this.type) {
            case LIKES :
                return likesBody();
            case FOLLOW:
                return followBody();
            case COMMENT:
                return commentBody();
            case REGISTER:
                return registerBody();
            default:
                return "";
        }
    }

    public String likesBody(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.sender.getNickname()).append("님이 작가님의 '").append(this.art.getName()).append("' 작품을 관심에 추가했어요");
        return sb.toString();
    }
    public String followBody(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.sender.getNickname()).append("님이 작가님을 팔로우해요");
        return sb.toString();
    }
    public String commentBody(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.sender.getNickname()).append("님이 작가님의 '").append(this.art.getName()).append("' 작품에 댓글을 달았어요");
        return sb.toString();
    }
    public String registerBody(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.sender.getNickname()).append("님이 '").append(this.art.getName()).append("'을 등록했어요");
        return sb.toString();
    }
}
