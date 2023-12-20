package plain.spring.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.exhibitioncomment.domain.ExhibitionComment;
import plain.spring.search.domain.Search;

import java.util.Optional;

public interface SearchRepository extends JpaRepository<Search, Long> {
    @Query("SELECT e FROM ExhibitionComment e JOIN FETCH e.user WHERE e.id = :id")
    Optional<ExhibitionComment> findWithUserById(@Param("id") Long id);

}
