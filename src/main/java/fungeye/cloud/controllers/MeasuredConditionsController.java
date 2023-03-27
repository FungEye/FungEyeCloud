package fungeye.cloud.controllers;

import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // not sure if we needed todo
@RestController
@RequestMapping("/")
public class MeasuredConditionsController {

    private MeasuredConditionRepository repository;

    public MeasuredConditionsController(MeasuredConditionRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MeasuredCondition getMeasuredConditionsById(@RequestParam MeasuredConditionId id)
    {
        return repository.findById(id).orElseThrow();
    }
}
