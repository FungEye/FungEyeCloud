package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
    List<Box> findByUserEntity_UsernameOrderByIdAsc(String username);
    List<Box> findByUserEntity_Username(String username);
    List<Box> findBoxesByUserEntity_Username(String username);
}