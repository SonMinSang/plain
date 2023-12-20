package plain.spring.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plain.spring.tag.domain.Tag;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
