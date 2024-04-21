package plain.spring.arttag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.art.domain.Art;
import plain.spring.arttag.domain.ArtTag;

import java.util.List;
import java.util.Optional;

public interface ArtTagRepository extends JpaRepository<ArtTag, Long> {

    @Query("SELECT a FROM ArtTag a JOIN FETCH a.tag WHERE a.art.id = :artId")
    List<ArtTag> findArtTagWithTagByArtId(@Param("artId") Long artId);

    @Modifying
    @Transactional
    @Query("DELETE FROM ArtTag a WHERE a.art.id = :artId")
    void deleteArtTagByArtId(@Param("artId") Long artId);
}
