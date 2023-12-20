package plain.spring.ExhibitionArtImage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plain.spring.ExhibitionArtImage.domain.ExhibitionArtImage;

public interface ExhibitionArtImageRepository extends JpaRepository<ExhibitionArtImage, Long> {
}
