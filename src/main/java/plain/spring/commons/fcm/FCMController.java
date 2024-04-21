package plain.spring.commons.fcm;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.notification.domain.Notification;
import plain.spring.notification.domain.NotificationType;
import plain.spring.notification.repository.NotificationRepository;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

import java.util.Map;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.HEADER;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fcm")
public class FCMController {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final FCMNotificationService fcmNotificationService;
    @Operation(summary = "fcm 테스트",
            parameters = {
                    @Parameter(in = PATH, name = "deviceToken",
                            required = true, description = "deviceToken"),
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}")
            }
    )
    @PostMapping("/{deviceToken}")
    public ResponseEntity fcmTest(@PathVariable("deviceToken") String deviceToken){
        User user = userRepository.findById(1L).orElse(null);
        String id = SecurityUtil.getId().orElse(null);
        User receiver = userRepository.findById(Long.parseLong(id)).orElse(null);
        ObjectMapper objectMapper = new ObjectMapper();
        Notification notification = Notification.builder()
                .type(NotificationType.FOLLOW)
                .receiverId(receiver.getId())
                .sender(user)
                .build();
        notificationRepository.save(notification);
        FCMNotification response = new FCMNotification(notification);
        FCMNotificationRequest request = FCMNotificationRequest.builder()
                .deviceToken(deviceToken)
                .body(notification.getBody())
                    .data(objectMapper.registerModule(new JavaTimeModule()).convertValue(response, Map.class))
                    .build();
        fcmNotificationService.sendNotificationByToken(request);
        return ResponseEntity.ok().build();
    }
}
