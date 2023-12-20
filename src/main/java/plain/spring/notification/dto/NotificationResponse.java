package plain.spring.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plain.spring.notification.domain.Notification;
import plain.spring.notification.domain.NotificationStatus;
import plain.spring.notification.domain.NotificationType;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Schema(description = "전시내 작품 정보")
@AllArgsConstructor
@Builder
public class NotificationResponse {
    private Long id;
    private NotificationType type;
    private NotificationStatus status;
    private String imgUrl;
    private Long senderId;
    private Long artId;
    private String message;
    private LocalDateTime createdAt;
    public NotificationResponse(Notification notification){
        this.id = notification.getId();
        this.type = notification.getType();
        this.status = notification.getStatus();
        this.message = notification.getBody();
        if (this.type != NotificationType.WELCOME){
            this.imgUrl = notification.getSender().getProfileImgUrl();
            this.senderId = notification.getSender().getId();
        }
        if (this.type == NotificationType.LIKES || this.type == NotificationType.COMMENT){
            this.artId = notification.getArt().getId();
        }
        this.createdAt = notification.getCreatedAt();
    }
}
