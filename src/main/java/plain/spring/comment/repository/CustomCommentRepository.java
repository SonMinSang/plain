package plain.spring.comment.repository;

import org.springframework.data.repository.query.Param;
import plain.spring.art.domain.Art;
import plain.spring.comment.domain.Comment;

import java.util.List;

public interface CustomCommentRepository {
    List<Comment> findAllWithUserByArt(Art art, Long userId);
}
