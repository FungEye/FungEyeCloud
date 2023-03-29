package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.MeasuredConditionsListDto;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import org.springframework.http.MediaType;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@CrossOrigin // not sure if we needed todo
@RestController
@RequestMapping("/")
public class MeasuredConditionsController {

    private MeasuredConditionRepository repository;
    private static final String PATTERN_FORMAT = "dd.MM.yyyy";
    public MeasuredConditionsController(MeasuredConditionRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/measuredConditions/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public MeasuredCondition getMeasuredConditionsById(@RequestParam MeasuredConditionId id)
    {
        Instant instant = id.getDateTime();

        return ;
    }

    @GetMapping("/measuredConditions")
    public MeasuredConditionsListDto getAllMeasuredConditions()
    {
        MeasuredConditionsListDto dto = new MeasuredConditionsListDto();
        dto.getMeasuredConditionList();

        return dto;
    }
}
