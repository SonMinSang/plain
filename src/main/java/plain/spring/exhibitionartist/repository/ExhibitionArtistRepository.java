package plain.spring.exhibitionartist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plain.spring.exhibitionartist.domain.ExhibitionArtist;

public interface ExhibitionArtistRepository extends JpaRepository<ExhibitionArtist, Long> {
}
