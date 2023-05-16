package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.dtos.MushroomUpdateDto;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.service.MushroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/")
public class MushroomController {
    private MushroomService service;

    public MushroomController(MushroomService service) {
        this.service = service;
    }

    @PostMapping("/mushroom")
    public ResponseEntity<MushroomDto> createMushroom(@RequestBody MushroomCreationDTO dto)
    {
        MushroomDto saved = service.createMushroom(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping(value = "/mushroom/{id}")
    public ResponseEntity<MushroomDto> getMushroomById(@PathVariable Long id)
    {
        MushroomDto found = service.getById(id);
        return new ResponseEntity<>(found, HttpStatus.FOUND);
    }

    @GetMapping(value = "/mushroom")
    public ResponseEntity<List<MushroomDto>> getAllMushrooms()
    {
        List<MushroomDto> all = service.getAll();
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @PostMapping(value = "/mushroom")
    public ResponseEntity<MushroomUpdateDto> updateMushroom(@RequestBody MushroomUpdateDto dto)
    {
        //Mushroom update = service.updateMushroom(dto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
