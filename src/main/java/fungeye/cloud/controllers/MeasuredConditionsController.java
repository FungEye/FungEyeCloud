package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.measured.HistoricalMeasurementDto;
import fungeye.cloud.domain.dtos.measured.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.measured.SearchConditionsParam;
import fungeye.cloud.service.MeasuredConditionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/")
public class MeasuredConditionsController {

    private MeasuredConditionsService service;

    public MeasuredConditionsController(MeasuredConditionsService service) {
        this.service = service;
    }

    @GetMapping(value = "box{id}/measurements")
    public ResponseEntity<List<MeasuredConditionDto>> getMeasuredConditions(@PathVariable("id") Long id,
                                                                            @RequestParam Optional<Integer> day,
                                                                            @RequestParam Optional<Integer> month,
                                                                            @RequestParam Optional<Integer> year,
                                                                            @RequestParam Optional<Integer> hour,
                                                                            @RequestParam Optional<Integer> minute) {
        SearchConditionsParam param = new SearchConditionsParam();

        day.ifPresent(param::setDay);
        month.ifPresent(param::setMonth);
        year.ifPresent(param::setYear);
        hour.ifPresent(param::setHour);
        minute.ifPresent(param::setMinute);
        param.setId(id);

        return new ResponseEntity<>(service.getMeasuredConditions(param), HttpStatus.OK);
    }

    @GetMapping(value = "box{id}/measurements/historical")
    public ResponseEntity<HistoricalMeasurementDto> getHistoricalMeasurements(@PathVariable("id") Long id, @RequestHeader(name = "Authorization") String token) {

        return new ResponseEntity<>(service.getHistoricalMeasurements(id, token), HttpStatus.OK);
    }


    @GetMapping(value = "box{id}/measurements/latest")
    public ResponseEntity<MeasuredConditionDto> getLatestMeasurements(@PathVariable("id") Long id,
                                                                      @RequestHeader(name = "Authorization") String token,
                                                                      @RequestParam Optional<Boolean> stage) {
        if (stage.isEmpty() || Boolean.FALSE.equals(stage.get())) {
            return new ResponseEntity<>(service.getLatestMeasuredCondition(id, token), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.getLatestMeasuredConditionWithStage(id, token), HttpStatus.OK);
        }
    }

    @GetMapping(value = "{username}/measurements/latest")
    public ResponseEntity<List<MeasuredConditionDto>> getAllLatestForUser(@PathVariable String username,
                                                                          @RequestHeader(name = "Authorization") String token,
                                                                          @RequestParam Optional<Boolean> stage) {
        if (stage.isEmpty() || Boolean.FALSE.equals(stage.get())) {
            return new ResponseEntity<>(service.getLatestForUser(username, token), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(service.getLatestForUserWithStage(username, token), HttpStatus.OK);
        }
    }
}
