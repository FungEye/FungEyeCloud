package fungeye.cloud.persistence.repository;

import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdealConditionRepository extends JpaRepository<IdealCondition, IdealConditionId> {
}