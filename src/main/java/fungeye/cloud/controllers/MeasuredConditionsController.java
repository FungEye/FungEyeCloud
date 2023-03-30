package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.*;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import fungeye.cloud.service.MeasuredConditionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@CrossOrigin // not sure if we needed todo
@RestController
@RequestMapping("/")
public class MeasuredConditionsController {

    private MeasuredConditionsService service;

    public MeasuredConditionsController(MeasuredConditionsService service) {
        this.service = service;
    }

    @GetMapping(value = "box{id}/measurements")
    public ResponseEntity<List<MeasuredConditionDto>> getMeasuredConditions(@PathVariable("id")Long id,
                                                                            @RequestParam Optional<Integer> day,
                                                                            @RequestParam Optional<Integer> month,
                                                                            @RequestParam Optional<Integer> year,
                                                                            @RequestParam Optional<Integer> hour,
                                                                            @RequestParam Optional<Integer> minute)

    {
        SearchConditionsParam param = new SearchConditionsParam();

        day.ifPresent(param::setDay);
        month.ifPresent(param::setMonth);
        year.ifPresent(param::setYear);
        hour.ifPresent(param::setHour);
        minute.ifPresent(param::setMinute);
        param.setId(id);

       return new ResponseEntity<>(service.getMeasuredConditions(param), HttpStatus.OK);
    }

    @GetMapping(value = "box{id}/measurements/latest")
    public ResponseEntity<MeasuredConditionDto> getLatestMeasurements(@PathVariable("id")Long id)
    {
        return new ResponseEntity<>(service.getLatestMeasuredCondition(id), HttpStatus.OK);
    }

}
