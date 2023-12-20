package plain.spring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.user.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    Optional<User> findOneByOauthId(String oauthId);

    @Query("SELECT u FROM User u JOIN FETCH u.arts WHERE u.id = :id")
    Optional<User> findOneWithArtsById(@Param("id") Long id);
    Boolean existsOneByNickname(String nickname);
}
