package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.HistoricalMeasurementDto;
import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.SearchConditionsParam;
import fungeye.cloud.domain.dtos.SingleMeasurementDto;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static fungeye.cloud.service.mappers.MeasuredConditionsMapper.*;

@Service
public class MeasuredConditionsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasuredConditionsService.class);

    private final MeasuredConditionRepository repository;
    private final BoxRepository boxRepository;

    public MeasuredConditionsService(MeasuredConditionRepository repository,
                                     BoxRepository boxRepository) {
        this.repository = repository;
        this.boxRepository = boxRepository;
    }

    public MeasuredConditionDto getLatestMeasuredCondition(long boxId) {
        return mapToDto(repository.findTopByBox_IdOrderByIdDesc(boxId));
    }

    public List<MeasuredConditionDto> getMeasuredConditions(SearchConditionsParam param) {
        List<MeasuredCondition> conditions = repository.findAllByBox_Id(param.getId());
        List<MeasuredCondition> result = new ArrayList<>();

        for (MeasuredCondition condition : conditions) {
            LocalDateTime dateTime = LocalDateTime.ofInstant(condition.getId().getDateTime(), ZoneId.systemDefault());

            if (!(
                    (param.getYear() != null && dateTime.getYear() != param.getYear()) ||
                            (param.getMonth() != null && dateTime.getMonthValue() != param.getMonth()) ||
                            (param.getDay() != null && dateTime.getDayOfMonth() != param.getDay()) ||
                            (param.getHour() != null && dateTime.getHour() != param.getHour()) ||
                            (param.getMinute() != null && dateTime.getMinute() != param.getMinute()))) {
                result.add(condition);
            }
        }
        return mapToDtoList(result);
    }

    public void addMeasuredCondition(MeasuredConditionDto dto) {
        MeasuredCondition toCreate = mapToEntity(dto);
        toCreate.setBox(boxRepository.getReferenceById(dto.getId().getBoxId()));
        MeasuredConditionDto response = mapToDto(repository.save(toCreate));
        LOGGER.info(String.format("Measurement persisted in database for box # %d", response.getId().getBoxId()));
    }

    public HistoricalMeasurementDto getHistoricalMeasurements(Long boxId) {
        List<MeasuredCondition> measuredConditions = repository.findAllByBox_Id(boxId);
        HistoricalMeasurementDto result = new HistoricalMeasurementDto();
        result.setTemperature(new ArrayList<>());
        result.setLight(new ArrayList<>());
        result.setHumidity(new ArrayList<>());
        result.setCo2(new ArrayList<>());
        for (MeasuredCondition cond: measuredConditions
             ) {
            // Temperature
            SingleMeasurementDto temp = new SingleMeasurementDto();
            temp.setValue(cond.getTemperature());
            temp.setDateTime(LocalDateTime.ofInstant(cond.getId().getDateTime(), ZoneId.systemDefault()));
            result.getTemperature().add(temp);

            // Humidity
            SingleMeasurementDto humidity = new SingleMeasurementDto();
            humidity.setValue(cond.getHumidity());
            humidity.setDateTime(LocalDateTime.ofInstant(cond.getId().getDateTime(), ZoneId.systemDefault()));
            result.getHumidity().add(humidity);

            // CO2
            SingleMeasurementDto co2 = new SingleMeasurementDto();
            co2.setValue(cond.getCo2());
            co2.setDateTime(LocalDateTime.ofInstant(cond.getId().getDateTime(), ZoneId.systemDefault()));
            result.getCo2().add(co2);

            // Light
            SingleMeasurementDto light = new SingleMeasurementDto();
            light.setValue(cond.getLight());
            light.setDateTime(LocalDateTime.ofInstant(cond.getId().getDateTime(), ZoneId.systemDefault()));
            result.getLight().add(light);
        }

        return result;
    }
}
