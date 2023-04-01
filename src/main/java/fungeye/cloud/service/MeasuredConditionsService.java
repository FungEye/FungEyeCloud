package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.SearchConditionsParam;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static fungeye.cloud.service.mappers.MeasuredConditionsMapper.mapToDto;
import static fungeye.cloud.service.mappers.MeasuredConditionsMapper.mapToDtoList;

@Service
public class MeasuredConditionsService {

    private MeasuredConditionRepository repository;

    public MeasuredConditionsService(MeasuredConditionRepository repository) {
        this.repository = repository;
    }

    public MeasuredConditionDto getLatestMeasuredCondition(long boxId) {
        return mapToDto(repository.findTopByBox_IdOrderByIdDesc(boxId));
    }

    public List<MeasuredConditionDto> getMeasuredConditions(SearchConditionsParam param) {
        List<MeasuredCondition> conditions = repository.findAllByBox_Id(param.getId());
        List<MeasuredCondition> result = new ArrayList<>();

        for (int i = 0; i < conditions.size(); i++) {
            LocalDateTime dateTime = LocalDateTime.ofInstant(conditions.get(i).getId().getDateTime(), ZoneOffset.ofHours(0));

            if (!(
                    (param.getYear() != null && dateTime.getYear() != param.getYear()) ||
                    (param.getMonth() != null && dateTime.getMonthValue() != param.getMonth()) ||
                    (param.getDay() != null && dateTime.getDayOfMonth() != param.getDay()) ||
                    (param.getHour() != null && dateTime.getHour() != param.getHour()) ||
                    (param.getMinute() != null && dateTime.getMinute() != param.getMinute()))) {
                result.add(conditions.get(i));
            }
        }
        return mapToDtoList(result);

    }
}