package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.Mushroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MushroomRepository extends JpaRepository<Mushroom, Long> {
    @Transactional
    @Modifying
    @Query("update Mushroom m set m.archived = ?1 where m.id = ?2")
    void updateArchivedById(Boolean archived, Long id);
    List<Mushroom> findByUser_Username(String username);
    List<Mushroom> findByUser_Id(Integer id);
    }