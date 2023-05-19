package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasuredConditionRepository extends JpaRepository<MeasuredCondition, MeasuredConditionId> {
    MeasuredCondition findFirstById_BoxIdOrderById_DateTimeDesc(Long boxId);

    MeasuredCondition findTopByBox_IdOrderByIdDesc(Long boxId);
    List<MeasuredCondition> findAllByBox_Id(long boxId);


}
