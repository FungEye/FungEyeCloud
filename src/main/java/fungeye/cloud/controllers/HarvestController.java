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

    // Not sure why it is complaining about the bean not being found, but the tests work
    public HarvestController(HarvestService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<HarvestDetailsDto> createHarvest(@RequestBody HarvestCreationDto dto) {
        return new ResponseEntity<>(service.addHarvest(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<HarvestDetailsDto>> getAllHarvestsByUsername(@PathVariable(name = "username") String username) {

        return new ResponseEntity<>(service.getAllHarvestsByUsername(username), HttpStatus.FOUND);
    }


}
