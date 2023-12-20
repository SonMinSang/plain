package plain.spring.exhibitionart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plain.spring.exhibitionart.domain.ExhibitionArt;

public interface ExhibitionArtRepository extends JpaRepository<ExhibitionArt, Long> {

}
