package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.BoxCreationDto;
import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.dtos.SimpleBoxGrowDto;
import fungeye.cloud.service.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class BoxController {

    private BoxService service;

    public BoxController(BoxService service) {
        this.service = service;
    }

    @PostMapping("/box")
    public ResponseEntity<BoxDto> createBox(BoxCreationDto dto) {
        return new ResponseEntity<>(service.createBox(dto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/box{id}")
    public ResponseEntity<BoxDetailsDto> getBoxById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/boxes")
    ResponseEntity<List<BoxDetailsDto>> getAllBoxes() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}/boxes/empty")
    ResponseEntity<List<BoxDto>> getAllEmptyBoxesByUserName(@PathVariable String username) {
        return new ResponseEntity<>(service.getAllEmptyByUserName(username), HttpStatus.OK);
    }

    @GetMapping(value = "/{username}/boxes")
    ResponseEntity<List<SimpleBoxGrowDto>> getAllBoxesByUsername(@PathVariable String username) {
        return new ResponseEntity<>(service.getAllByUsername(username), HttpStatus.OK);
    }
}

