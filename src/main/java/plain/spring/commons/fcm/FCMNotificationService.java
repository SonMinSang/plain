package plain.spring.commons.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

    private final FirebaseMessaging firebaseMessaging;

    public void sendNotificationByToken(FCMNotificationRequest request){
        Notification notification = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getBody())
                .build();
        Message message = Message.builder()
                .setToken(request.getDeviceToken())
                .setNotification(notification)
                .putAllData(request.getData())
                .build();
        try {
            firebaseMessaging.send(message);
            System.out.println("알림 성공");
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendNotificationsByToken(List<FCMNotificationRequest> requests){
        List<Message> messages = new ArrayList<>();
        for (FCMNotificationRequest request : requests){
            Notification notification = Notification.builder()
                    .setTitle(request.getTitle())
                    .setBody(request.getBody())
                    .build();
            Message message = Message.builder()
                    .setToken(request.getDeviceToken())
                    .setNotification(notification)
                    .putAllData(request.getData())
                    .build();
            messages.add(message);
        }
        firebaseMessaging.sendAllAsync(messages);
    }
}
