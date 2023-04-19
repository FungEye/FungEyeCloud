package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.SearchConditionsParam;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static fungeye.cloud.service.mappers.MeasuredConditionsMapper.*;

//@Transactional TODO commented out for now - try with fix below
@Service
public class MeasuredConditionsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasuredConditionsService.class);

    private MeasuredConditionRepository repository;
    private BoxService boxService;
    private final BoxRepository boxRepository;

    public MeasuredConditionsService(MeasuredConditionRepository repository, BoxService boxService,
                                     BoxRepository boxRepository) {
        this.repository = repository;
        this.boxService = boxService;
        LOGGER.info("MS Service init");
        this.boxRepository = boxRepository;
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

    public MeasuredConditionDto addMeasuredCondition(MeasuredConditionDto dto) {
        LOGGER.info("addMeasuredCondition called");
        MeasuredCondition toCreate = mapToEntity(dto);
        LOGGER.info("addMeasuredCondition entity created");


        toCreate.setBox(boxRepository.getReferenceById(dto.getId().getBoxId()));
        // toCreate.setBox(mapFromBoxDto(boxService.getById(1L)));
        LOGGER.info("addMeasuredCondition box retrieved");


        return mapToDto(repository.save(toCreate));
    }
}
