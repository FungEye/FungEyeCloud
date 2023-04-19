package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.SearchConditionsParam;
import fungeye.cloud.service.MeasuredConditionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //TODO For testing only!
    @PostMapping("/measurements")
    public ResponseEntity<MeasuredConditionDto> addMeasurement(@RequestBody MeasuredConditionDto dto) {
        return new ResponseEntity<>(service.addMeasuredCondition(dto), HttpStatus.CREATED);
    }

}
