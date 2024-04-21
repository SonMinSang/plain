package plain.spring.block;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BlockRepository extends JpaRepository<Block, Long> {

    @Query("SELECT COUNT(b) FROM Block b " +
            "WHERE (b.blocker.id = :userId1 AND b.blocked.id = :userId2) " +
            "OR (b.blocker.id = :userId2 AND b.blocked.id = :userId1)")
    Long countBlock(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    @Query("SELECT b FROM Block b WHERE b.blocker.id = :userId1 AND b.blocked.id = :userId2")
    Optional<Block> findBlockByIds(@Param("userId1") Long userId1, @Param("userId2") Long userId2);


}
