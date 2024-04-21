package plain.spring.exhibitioncomment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import plain.spring.exhibition.domain.Exhibition;
import plain.spring.exhibitioncomment.domain.ExhibitionComment;

import java.util.List;
import java.util.Optional;

public interface ExhibitionCommentRepository extends JpaRepository<ExhibitionComment, Long> {
    @Query("SELECT e FROM ExhibitionComment e JOIN FETCH e.user WHERE e.id = :id")
    Optional<ExhibitionComment> findWithUserById(@Param("id") Long id);

    @Query("SELECT e FROM ExhibitionComment e JOIN FETCH e.user JOIN FETCH e.exhibition WHERE e.id = :id")
    Optional<ExhibitionComment> findWithUserAndExhibitionById(@Param("id") Long id);

    @Query("SELECT e FROM ExhibitionComment e JOIN FETCH e.user WHERE e.exhibition = :exhibition")
    List<ExhibitionComment> findAllWithUserByArt(@Param("exhibition")Exhibition exhibition);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM ExhibitionComment e WHERE e.user.id = :userId")
    void deleteAllByUserId(@Param("userId")Long userId);

}
