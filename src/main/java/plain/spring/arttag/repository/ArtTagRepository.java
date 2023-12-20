package plain.spring.arttag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.arttag.domain.ArtTag;

public interface ArtTagRepository extends JpaRepository<ArtTag, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM ArtTag a WHERE a.art.id = :artId")
    void deleteArtTagByArtId(@Param("artId") Long artId);
}
