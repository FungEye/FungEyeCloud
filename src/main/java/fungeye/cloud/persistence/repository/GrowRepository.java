package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Grow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowRepository extends JpaRepository<Grow, Long> {
    Grow findFirstByBox_IdOrderByDateStartedDesc(Long id);
    Grow findByBox_IdAndIsActive(Long id, Boolean isActive);
    List<Grow> findByBox_Id(Long id);
    List<Grow> findGrowsByBox_UserEntity_Username(String username);
}