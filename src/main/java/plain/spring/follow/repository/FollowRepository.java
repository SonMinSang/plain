package plain.spring.follow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.follow.domain.Follow;
import plain.spring.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {


    @Query("SELECT f FROM Follow f WHERE f.follower.id = :followerId AND f.following.id = :followingId")
    Optional<Follow> findByFollowerAndFollowing(@Param("followerId") Long followerId, @Param("followingId") Long followingId);

    @Query("SELECT f FROM Follow f JOIN FETCH f.following WHERE f.follower.id = :userId")
    List<Follow> findAllFollowingByFollowerId(@Param("userId") Long userId);
    @Query("SELECT f FROM Follow f JOIN FETCH f.follower WHERE f.following.id = :userId")
    List<Follow> findAllFollowerByFollowingId(@Param("userId") Long userId);

    @Query("SELECT u FROM User u WHERE u.followedCount > 0 order by RAND() limit 2")
    List<User> findByOrderByFollowsCountDescWithArtist();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Follow f WHERE f.follower.id = :userId OR f.following.id = :userId")
    void deleteAllFollowByUserId(@Param("userId") Long userId);
}
