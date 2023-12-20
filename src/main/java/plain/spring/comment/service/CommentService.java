package plain.spring.comment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.art.domain.Art;
import plain.spring.art.repository.ArtRepository;
import plain.spring.comment.dto.CommentDisplay;
import plain.spring.comment.dto.CommentPost;
import plain.spring.comment.domain.Comment;
import plain.spring.comment.repository.CommentRepository;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.commons.fcm.FCMNotificationRequest;
import plain.spring.commons.fcm.FCMNotificationService;
import plain.spring.notification.domain.Notification;
import plain.spring.notification.dto.NotificationResponse;
import plain.spring.notification.repository.NotificationRepository;
import plain.spring.notification.domain.NotificationType;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArtRepository artRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;
    private final FCMNotificationService fcmNotificationService;
    public List<CommentDisplay> getAllCommentsByArtId(Long artId){
        Art art = artRepository.findById(artId).orElseThrow();
        List<Comment> comments = commentRepository.findAllWithUserByArt(art);
        List<CommentDisplay> results = comments.stream().map(CommentDisplay::new).collect(Collectors.toList());
        return results;
    }

    public CommentDisplay postComment(Long artId, CommentPost commentPost){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        User user = userRepository.findById(userId).orElseThrow();
        Art art = artRepository.findByIdWithArtist(artId).orElseThrow();
        Comment comment = Comment.builder()
                .content(commentPost.getContent())
                .user(user)
                .art(art)
                .build();
        commentRepository.save(comment);
        ObjectMapper objectMapper = new ObjectMapper();
        Notification notification = Notification.builder()
                .type(NotificationType.COMMENT)
                .receiverId(art.getArtist().getId())
                .sender(user)
                .art(art)
                .build();
        notificationRepository.save(notification);
        NotificationResponse response = new NotificationResponse(notification);
        FCMNotificationRequest request = FCMNotificationRequest.builder()
                .deviceToken(art.getArtist().getDeviceToken())
                .image(user.getProfileImgUrl())
                .body(notification.getBody())
                .data(objectMapper.registerModule(new JavaTimeModule()).convertValue(response, Map.class))
                .build();
        fcmNotificationService.sendNotificationByToken(request);
        return new CommentDisplay(comment);
    }

    public CommentDisplay editComment(Long commentId, CommentPost commentPost){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        Comment comment = commentRepository.findWithUserById(commentId).orElseThrow();
        if (userId == comment.getUser().getId()){
            comment.edit(commentPost.getContent());
        }
        return new CommentDisplay(comment);
    }

    public void deleteComment(Long commentId){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        Comment comment = commentRepository.findWithUserById(commentId).orElseThrow();
        if (userId == comment.getUser().getId()){
            commentRepository.delete(comment);
        }
    }
}
