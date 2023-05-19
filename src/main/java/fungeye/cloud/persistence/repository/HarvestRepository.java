package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    List<Harvest> findByGrow_IdOrderByDateHarvestedDesc(Long id);
    List<Harvest> findByGrow_Box_UserEntity_UsernameOrderByDateHarvestedDesc(String username);
}