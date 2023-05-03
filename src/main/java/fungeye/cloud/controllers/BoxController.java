package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.service.BoxService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //not sure if needed todo
@RestController
@RequestMapping("/")
public class BoxController {

    private BoxService service;

    public BoxController(BoxService service) {
        this.service = service;
    }

    @PostMapping("/box")
    public ResponseEntity<BoxDto> createBox()
    {
        return new ResponseEntity<>(service.createBox(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/box{id}")
    public ResponseEntity<BoxDetailsDto> getBoxById(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
}
