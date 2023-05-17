package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Box;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoxRepository extends JpaRepository<Box, Long> {
    List<Box> findBoxesByUserEntity_Username(String username);
}