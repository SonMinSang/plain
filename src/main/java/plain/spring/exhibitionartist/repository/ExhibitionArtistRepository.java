package plain.spring.exhibitionartist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.exhibitionartist.domain.ExhibitionArtist;

import java.util.List;

public interface ExhibitionArtistRepository extends JpaRepository<ExhibitionArtist, Long> {
    @Query("SELECT e FROM ExhibitionArtist e JOIN FETCH e.exhibition WHERE e.user.id = :id")
    List<ExhibitionArtist> findAllByArtistWithExhibitions(@Param("id") Long id);
}
