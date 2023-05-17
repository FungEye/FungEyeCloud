package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Grow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrowRepository extends JpaRepository<Grow, Long> {
    List<Grow> findByBox_Id(Long id);
    List<Grow> findGrowsByUserEntity_Username(String username);
}