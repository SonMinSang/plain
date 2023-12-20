package plain.spring.art.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import plain.spring.art.domain.Art;
import plain.spring.art.dto.ArtWithFollowAndLikes;
import plain.spring.art.dto.ArtWithLikes;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomArtRepository {


    List<ArtWithLikes> findAllArtsByCategoryWithLikes(String category, Long id, Long userId);

    Slice<Art> findArtsByQueryAndCategory(String query, String category, Pageable pageable);

    Optional<ArtWithFollowAndLikes> findArtByIdWithFollowAndLikes(Long artId, Long userId);

    List<Art> findSimilarArts(List<String> tags, Long artId);

}
