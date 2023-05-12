package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
}