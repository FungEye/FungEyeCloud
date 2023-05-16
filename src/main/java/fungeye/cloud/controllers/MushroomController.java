package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.CustomMushroomCreationDto;
import fungeye.cloud.domain.dtos.DefaultMushroomCreationDto;
import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.service.MushroomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/")
public class MushroomController {
    private MushroomService service;

    public MushroomController(MushroomService service) {
        this.service = service;
    }

    @PostMapping("/mushroom")
    public ResponseEntity<MushroomDto> createMushroom(
            @RequestBody MushroomCreationDTO dto) {
        MushroomDto saved = service.createMushroom(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/mushroom/default")
    public ResponseEntity<MushroomDto> createDefaultMushroom(
            @RequestBody DefaultMushroomCreationDto dto) {
        MushroomDto saved = service.createDefaultMushroom(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/mushroom/custom")
    public ResponseEntity<MushroomDto> createCustomMushroom(
            @RequestBody MushroomCreationDTO dto) {
        // They do the same thing, but this one isn't admin protected
        MushroomDto saved = service.createMushroom(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // New endpoint using the username instead and with conditions
    @PostMapping("/mushroom/custom/conditions")
    public ResponseEntity<MushroomDto> createCustomMushroomWithConditions(
            @RequestBody CustomMushroomCreationDto dto) {
        // They do the same thing, but this one isn't admin protected
        MushroomDto saved = service.createCustomMushroom(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping(value = "/mushroom/{id}")
    public ResponseEntity<MushroomDto> getMushroomById(@PathVariable Long id) {
        MushroomDto found = service.getByMushroomId(id);
        return new ResponseEntity<>(found, HttpStatus.FOUND);
    }

    @GetMapping(value = "/mushroom")
    public ResponseEntity<List<MushroomDto>> getAllDefaultMushrooms() {
        List<MushroomDto> all = service.getAllDefault();
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @GetMapping(value = "/mushroom/custom/{userId}")
    public ResponseEntity<List<MushroomDto>> getDefaultAndCustom(@PathVariable String username)
    {
        List<MushroomDto> allDefault = service.getAllDefault();
        List<MushroomDto> custom = service.getCustom(username);
        custom.addAll(allDefault);
        return new ResponseEntity<>(custom, HttpStatus.FOUND);
    }

    @PutMapping(value ="/mushroom/{id}")
    public ResponseEntity<String> archiveMushroom(@PathVariable long id, @RequestHeader(name = "Authorization") String token) {
        service.archiveMushroom(id, token);
        return ResponseEntity.ok("Archived");
    }
}
