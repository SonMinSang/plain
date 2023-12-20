package plain.spring.art.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.art.domain.Art;


import java.util.List;
import java.util.Optional;

public interface ArtRepository extends JpaRepository<Art, Long>, CustomArtRepository{

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Art> findById(Long artId);

    @Modifying
    @Query("update Art a set a.likesCount = a.likesCount + 1 where a.id = :artId")
    void incrementLikesCount(@Param("artId") Long artId);

    @Modifying
    @Query("update Art a set a.likesCount = a.likesCount - 1 where a.id = :artId")
    void decreaseLikesCount(@Param("artId") Long artId);

    @Query("SELECT a FROM Art a JOIN FETCH a.artist WHERE a.likesCount > 0 order by RAND() limit 2")
    List<Art> findAllOrderByLikesCountDescWithArtist();

    @Query("SELECT a FROM Art a JOIN FETCH a.artist WHERE a.id = :artId")
    Optional<Art> findByIdWithArtist(@Param("artId")Long artId);

    @Query("SELECT a FROM Art a WHERE a.artist.id = :artistId and a.id != :artId order by RAND() LIMIT 5")
    List<Art> findArtistsOtherArts(@Param("artistId") Long artistId, @Param("artId") Long artId);
}
