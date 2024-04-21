package plain.spring.exhibitioncomment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.comment.dto.CommentDisplay;
import plain.spring.comment.dto.CommentPost;
import plain.spring.commons.util.SecurityUtil;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.exhibition.repository.ExhibitionRepository;
import plain.spring.exhibitioncomment.domain.ExhibitionComment;
import plain.spring.exhibitioncomment.repository.ExhibitionCommentRepository;
import plain.spring.user.domain.User;
import plain.spring.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ExhibitionCommentService {
    private final ExhibitionCommentRepository exhibitionCommentRepository;
    private final ExhibitionRepository exhibitionRepository;
    private final UserRepository userRepository;
    public List<CommentDisplay> getAllCommentsByExhibitionId(Long exhibitionId){
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow();
        List<ExhibitionComment> comments = exhibitionCommentRepository.findAllWithUserByArt(exhibition);
        List<CommentDisplay> results = comments.stream().map(CommentDisplay::new).collect(Collectors.toList());
        return results;
    }

    public CommentDisplay postComment(Long exhibitionId, CommentPost commentPost){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        User user = userRepository.findById(userId).orElseThrow();
        Exhibition exhibition = exhibitionRepository.findById(exhibitionId).orElseThrow();
        ExhibitionComment comment = ExhibitionComment.builder()
                .content(commentPost.getContent())
                .user(user)
                .exhibition(exhibition)
                .build();
        exhibition.postComment();
        exhibitionCommentRepository.save(comment);
        return new CommentDisplay(comment);
    }

    public CommentDisplay editComment(Long commentId, CommentPost commentPost){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        ExhibitionComment comment = exhibitionCommentRepository.findWithUserById(commentId).orElseThrow();
        if (userId == comment.getUser().getId()){
            comment.edit(commentPost.getContent());
        }
        return new CommentDisplay(comment);
    }

    public void deleteComment(Long commentId){
        Long userId = Long.valueOf(SecurityUtil.getId().orElseThrow());
        ExhibitionComment comment = exhibitionCommentRepository.findWithUserAndExhibitionById(commentId).orElseThrow();
        if (userId == comment.getUser().getId()){
            exhibitionCommentRepository.delete(comment);
            comment.getExhibition().deleteComment();
        }
    }
}
