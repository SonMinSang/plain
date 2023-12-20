package plain.spring.exhibition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import plain.spring.exhibition.domain.Exhibition;

import java.util.Optional;


public interface ExhibitionRepository extends JpaRepository<Exhibition, Long>, CustomExhibitionRepository {
    @Query("SELECT e FROM Exhibition e WHERE e.id = :exhibitionId AND e.status = 'available'")
    Optional<Exhibition> findByIdAvailable(@Param("exhibitionId")Long exhibitionId);

}
