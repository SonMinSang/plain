package plain.spring.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plain.spring.policy.domain.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
