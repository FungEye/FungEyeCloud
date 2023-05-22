package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Grow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowRepository extends JpaRepository<Grow, Long> {
    List<Grow> findByBox_Id(Long id);
    List<Grow> findGrowsByBox_UserEntity_Username(String username);
}