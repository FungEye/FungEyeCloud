package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.*;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Optional;

@CrossOrigin // not sure if we needed todo
@RestController
@RequestMapping("/")
public class MeasuredConditionsController {

    private MeasuredConditionRepository repository;
    public MeasuredConditionsController(MeasuredConditionRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "box/{id}/measurements")
    public ResponseEntity<MeasuredConditionDto> getMeasuredConditions(@PathVariable("id")Long id,
                                                                      @RequestParam Optional<Integer> day,
                                                                      @RequestParam Optional<Integer> month,
                                                                      @RequestParam Optional<Integer> year,
                                                                      @RequestParam Optional<Integer> hour,
                                                                      @RequestParam Optional<Integer> minute)
    {
       return null;
    }

    @GetMapping(value = "box{id}/measurements/latest")
    public ResponseEntity<MeasuredConditionDto> getLatestMeasurements(@PathVariable("id")Long id)
    {
        MeasuredCondition measuredCondition = repository.findTopByBox_IdOrderByIdDesc(id);
        MeasuredConditionDto measuredConditionDto = new MeasuredConditionDto();
        Instant dt = measuredCondition.getId().getDateTime();

        LocalDateTime dtobject = LocalDateTime.ofInstant(dt, ZoneId.systemDefault());
        measuredConditionDto.setId(
                new MeasuredConditionIdDto(
                        measuredCondition.getId().getBoxId(),
                        new DateTimeDto(dtobject.getYear(), dtobject.getMonthValue(), dtobject.getDayOfMonth(),
                                dtobject.getHour(), dtobject.getMinute(), dtobject.getSecond()
                        )));
        measuredConditionDto.setHumidity(measuredCondition.getHumidity());
        measuredConditionDto.setTemperature(measuredCondition.getTemperature());
        measuredConditionDto.setBox(new BoxDto(id));

        return new ResponseEntity<>(measuredConditionDto, HttpStatus.OK);
    }

}
