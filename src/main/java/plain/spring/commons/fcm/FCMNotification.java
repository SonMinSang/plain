package plain.spring.commons.fcm;


import lombok.Getter;
import plain.spring.notification.domain.Notification;
import plain.spring.notification.domain.NotificationType;

import java.time.LocalDateTime;

@Getter
public class FCMNotification{
    private String type;
    private String status;
    private String senderId = "";
    private String artId = "";
    private String message;
    private String createdAt;

    public FCMNotification(Notification notification){
        this.type = notification.getType().name();
        this.status = notification.getStatus().name();
        this.message = notification.getBody();
        if (notification.getType() != NotificationType.WELCOME){
            this.senderId = notification.getSender().getId().toString();
        }
        if (notification.getType() == NotificationType.LIKES || notification.getType() == NotificationType.COMMENT){
            this.artId = notification.getArt().getId().toString();
        }
        this.createdAt = LocalDateTime.now().toString();
    }
}
