package plain.spring.exhibitionartthumbnailimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plain.spring.exhibitionartthumbnailimage.domain.ExhibitionArtThumbnailImage;

public interface ExhibitionArtThumbnailImageRepository extends JpaRepository<ExhibitionArtThumbnailImage, Long> {
}
