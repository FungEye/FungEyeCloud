package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.HarvestCreationDto;
import fungeye.cloud.domain.dtos.HarvestDetailsDto;
import fungeye.cloud.service.HarvestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/harvest")
public class HarvestController {

    private HarvestService service;

    public HarvestController(HarvestService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<HarvestDetailsDto> createHarvest(@RequestBody HarvestCreationDto dto) {
        return new ResponseEntity<>(service.addHarvest(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<HarvestDetailsDto>> getAllHarvestsByUserId(@PathVariable(name = "userId") int userId) {

        return new ResponseEntity<>(service.getAllHarvestsByUserId(userId), HttpStatus.FOUND);
    }


}
