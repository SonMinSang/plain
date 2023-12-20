package plain.spring.likes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.art.domain.Art;
import plain.spring.likes.domain.Likes;
import plain.spring.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findLikesByUserAndArt(User user, Art art);
    @Query("SELECT l FROM Likes l JOIN FETCH l.art a JOIN FETCH a.artist WHERE l.user.id = :userId")
    List<Likes> findLikesWithArtByUserId(@Param("userId") Long userId);
}
