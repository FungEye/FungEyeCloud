package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasuredConditionRepository extends JpaRepository<MeasuredCondition, MeasuredConditionId> {
}