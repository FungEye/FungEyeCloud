package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Box;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoxRepository extends JpaRepository<Box, Long> {
}