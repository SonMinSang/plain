package plain.spring.exhibition.repository;

import org.springframework.stereotype.Repository;
import plain.spring.exhibition.dto.ExhibitionWithLikes;
import plain.spring.exhibition.dto.ExhibitionSummary;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomExhibitionRepository {
    List<ExhibitionSummary> findAllAvailableExhibition(Long userId);
    Optional<ExhibitionWithLikes> findExhibitionWithLikes(Long exhibitionId, Long userId);
    List<Long> findUserFollows(Long userId, List<Long> userIds);
}
