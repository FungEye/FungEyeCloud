package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface MeasuredConditionRepository extends JpaRepository<MeasuredCondition, MeasuredConditionId> {

    MeasuredCondition findTopByBox_IdOrderByIdDesc(Long boxId);


}
