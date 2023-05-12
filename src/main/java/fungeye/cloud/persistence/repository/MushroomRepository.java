package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Mushroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MushroomRepository extends JpaRepository<Mushroom, Long> {
    List<Mushroom> findByUser_Id(Integer id);
}