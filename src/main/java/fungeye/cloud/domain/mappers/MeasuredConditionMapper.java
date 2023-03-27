package fungeye.cloud.domain.mappers;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.enities.MeasuredCondition;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeasuredConditionMapper {
    MeasuredCondition toEntity(MeasuredConditionDto measuredConditionDto);

    MeasuredConditionDto toDto(MeasuredCondition measuredCondition);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MeasuredCondition partialUpdate(MeasuredConditionDto measuredConditionDto, @MappingTarget MeasuredCondition measuredCondition);
}