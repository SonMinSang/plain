package plain.spring.job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plain.spring.job.domain.Job;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByName(String name);
}
