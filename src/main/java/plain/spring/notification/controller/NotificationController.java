package plain.spring.notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import plain.spring.notification.dto.NotificationResponse;
import plain.spring.notification.service.NotificationService;

import java.util.List;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notifications")
@Tag(name = "알림 관련 api")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "모든 알림 조회",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = QUERY, name = "lastId",
                            required = false, description = "마지막으로 반환받은 알림 ID, 없으면 null")
            }
    )
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotification(@RequestParam(value = "lastId", required = false) Long lastId){
        List<NotificationResponse> results = notificationService.findAllNotifications(lastId);
        return ResponseEntity.ok(results);
    }



    @Operation(summary = "알림 읽음 처리",
            parameters = {
                    @Parameter(in = HEADER, name = "Authorization",
                            required = true, description = "Bearer {jwt token}"),
                    @Parameter(in = PATH, name = "notificationId",
                            required = true, description = "알림 ID")
            }
    )
    @PutMapping("/{notificationId}")
    public ResponseEntity updateNotificationStatus(@PathVariable("notificationId") Long notificationId){
        notificationService.updateNotificationStatus(notificationId);
        return ResponseEntity.ok().build();
    }

}
