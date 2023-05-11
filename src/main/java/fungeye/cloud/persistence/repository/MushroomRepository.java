package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Mushroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MushroomRepository extends JpaRepository<Mushroom, Long> {

}