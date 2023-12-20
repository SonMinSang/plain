package plain.spring.exhibitionlikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.exhibitionlikes.domain.ExhibitionLikes;
import plain.spring.user.domain.User;

import java.util.Optional;

public interface ExhibitionLikesRepository extends JpaRepository<ExhibitionLikes, Long> {
    Optional<ExhibitionLikes> findLikesByUserAndExhibition(User user, Exhibition exhibition);
}
