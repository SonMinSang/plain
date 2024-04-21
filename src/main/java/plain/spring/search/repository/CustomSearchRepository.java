package plain.spring.search.repository;

import java.util.List;

public interface CustomSearchRepository {
    List<String> findSearchByUserId(Long userId);
}
