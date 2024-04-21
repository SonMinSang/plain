package plain.spring.exhibitionlikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.exhibitionlikes.domain.ExhibitionLikes;
import plain.spring.user.domain.User;

import java.util.Optional;

public interface ExhibitionLikesRepository extends JpaRepository<ExhibitionLikes, Long> {
    Optional<ExhibitionLikes> findLikesByUserAndExhibition(User user, Exhibition exhibition);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM ExhibitionLikes e WHERE e.user.id = :userId")
    void deleteLikesByUserId(@Param("userId") Long userId);
}
