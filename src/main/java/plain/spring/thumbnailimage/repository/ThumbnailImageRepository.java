package plain.spring.thumbnailimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import plain.spring.thumbnailimage.domain.ThumbnailImage;

public interface ThumbnailImageRepository extends JpaRepository<ThumbnailImage, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM ThumbnailImage t WHERE t.art.id = :artId")
    void deleteThumbnailImageByArtId(@Param("artId") Long artId);


}
