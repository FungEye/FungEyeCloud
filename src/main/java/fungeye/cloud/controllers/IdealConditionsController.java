package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.ideal.IdealConditionDto;
import fungeye.cloud.service.IdealConditionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class IdealConditionsController {
    private IdealConditionService service;

    public IdealConditionsController(IdealConditionService service) {
        this.service = service;
    }

    @PostMapping("/ideal")
    public ResponseEntity<IdealConditionDto> createIdealCondition(@RequestBody IdealConditionDto dto) {
        IdealConditionDto saved = service.createIdealCondition(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping(value = "/ideal/{mushroomId}")
    public ResponseEntity<List<IdealConditionDto>> getIdealConditionsByMushroomId(@PathVariable Long mushroomId) {
        List<IdealConditionDto> found = service.getByMushroomId(mushroomId);
        return new ResponseEntity<>(found, HttpStatus.OK);
    }
}
