package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasuredConditionRepository extends JpaRepository<MeasuredCondition, MeasuredConditionId> {
    MeasuredCondition findFirstById_BoxIdOrderById_DateTimeDesc(Long boxId);

    MeasuredCondition findTopByBox_IdOrderByIdDesc(Long boxId);
    List<MeasuredCondition> findAllByBox_Id(long boxId);


}
