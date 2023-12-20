package plain.spring.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.art.domain.Art;
import plain.spring.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.id = :id")
    Optional<Comment> findWithUserById(@Param("id") Long id);
    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.art = :art")
    List<Comment> findAllWithUserByArt(@Param("art") Art art);
}
