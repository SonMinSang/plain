package plain.spring.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.search.domain.Search;

import java.util.List;

public interface SearchRepository extends JpaRepository<Search, Long>, CustomSearchRepository {
    @Query("SELECT s FROM Search s WHERE s.user.id = :userId")
    List<Search> findAllSearchByUserId(@Param("userId") Long userId);

    @Query("SELECT s FROM Search s WHERE s.user.id = :userId AND s.query = :query")
    List<Search> findSearchByQueryAndUserId(@Param("userId") Long userId, @Param("query") String query);
}
