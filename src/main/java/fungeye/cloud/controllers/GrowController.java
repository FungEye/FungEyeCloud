package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.grow.GrowCreationDto;
import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.grow.GrowIdMushroomNameDto;
import fungeye.cloud.domain.dtos.grow.GrowUpdateDto;
import fungeye.cloud.service.GrowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class GrowController {

    private GrowService service;

    public GrowController(GrowService service) {
        this.service = service;
    }

    @PostMapping("/grow")
    public ResponseEntity<GrowDto> createGrow(GrowCreationDto dto)
    {
        return new ResponseEntity<>(service.createGrow(dto), HttpStatus.CREATED);
    }

    @GetMapping("/grow/{id}")
    public  ResponseEntity<GrowDto> getGrowById(@PathVariable Long id)
    {
        return new ResponseEntity<>(service.getGrowById(id), HttpStatus.OK);
    }

    @GetMapping("/grow/all")
    public ResponseEntity<List<GrowIdMushroomNameDto>>getGrowsByUsername (String username)
    {
        return new ResponseEntity<>(service.getAllGrowsByUsername(username), HttpStatus.OK);
    }

    @PutMapping("/grow/update")
    public ResponseEntity<GrowDto> updateGrow(GrowUpdateDto dto)
    {
        return new ResponseEntity<>(service.updateGrow(dto), HttpStatus.OK);
    }


    @PatchMapping("/grow/endGrow")
    public ResponseEntity<GrowDto> endGrow(Long id)
    {
        return new ResponseEntity<>(service.endGrow(id), HttpStatus.OK);
    }

}
