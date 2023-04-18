package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Grow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrowRepository extends JpaRepository<Grow, Long> {
}