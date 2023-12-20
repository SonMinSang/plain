package plain.spring.user.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import plain.spring.user.domain.User;

public interface CustomUserRepository {
    Slice<User> findAllArtistsByQueryAndCategory(String query, String category, Pageable pageable);
}
