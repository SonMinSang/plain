package plain.spring.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.image.domain.ArtImage;

public interface ArtImageRepository extends JpaRepository<ArtImage, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ArtImage a WHERE a.art.id = :artId")
    void deleteArtImageByArtId(@Param("artId") Long artId);

}
