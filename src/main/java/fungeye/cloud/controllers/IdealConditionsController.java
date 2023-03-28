package fungeye.cloud.controllers;

import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;
import fungeye.cloud.persistence.repository.IdealConditionRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // not sure if we needed todo
@RestController
@RequestMapping("/api")
public class IdealConditionsController {

    private IdealConditionRepository repository;

    public IdealConditionsController(IdealConditionRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/idealConditions", produces = {MediaType.APPLICATION_JSON_VALUE})
    public IdealCondition getIdealConditionById(@RequestParam IdealConditionId id)
    {
        return repository.findById(id).orElseThrow();
    }
}
