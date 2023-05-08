package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IdealConditionRepository extends JpaRepository<IdealCondition, IdealConditionId> {
    @Query("select i from IdealCondition i where i.mushroom.id = ?1")
    List<IdealCondition> findByMushroom_Id(Long id);

}