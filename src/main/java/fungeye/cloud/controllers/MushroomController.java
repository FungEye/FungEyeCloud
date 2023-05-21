package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.*;
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

    @Deprecated
    @PostMapping("/mushroom")
    public ResponseEntity<MushroomDto> createMushroom(
            @RequestBody MushroomCreationDTO dto) {
        MushroomDto saved = service.createMushroom(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping("/mushroom/default")
    public ResponseEntity<MushroomWithConditionsDto> createDefaultMushroom(
            @RequestBody DefaultMushroomCreationDto dto) {
        MushroomWithConditionsDto saved = service.createDefaultMushroom(dto);
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
    public ResponseEntity<MushroomWithConditionsDto> createCustomMushroomWithConditions(
            @RequestBody CustomMushroomCreationDto dto) {
        // They do the same thing, but this one isn't admin protected
        MushroomWithConditionsDto saved = service.createCustomMushroom(dto);
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

    @GetMapping(value = "/mushroom/custom/{username}")
    public ResponseEntity<List<MushroomDto>> getDefaultAndCustom(@PathVariable String username) {
        List<MushroomDto> allDefault = service.getAllDefault();
        List<MushroomDto> custom = service.getCustom(username);
        custom.addAll(allDefault);
        return new ResponseEntity<>(custom, HttpStatus.FOUND);
    }

    @PutMapping(value = "/mushroom/{id}")
    public ResponseEntity<String> archiveMushroom(@PathVariable long id, @RequestHeader(name = "Authorization") String token) {
        service.archiveMushroom(id, token);
        return ResponseEntity.ok("Archived");
    }

    @PutMapping(value = "/mushroom/update")
    public ResponseEntity<MushroomDto> updateMushroom(@RequestBody MushroomUpdateDto dto) {
        return new ResponseEntity<>(service.updateMushroom(dto), HttpStatus.OK);
    }

    // Calling them mushrooms to distinguish between the new and old endpoints
    @GetMapping(value = "/mushrooms")
    public ResponseEntity<List<MushroomWithConditionsDto>> getDefaultWithConditions() {
        List<MushroomWithConditionsDto> allDefault = service.getDefaultMushroomsWithConditions();
        return new ResponseEntity<>(allDefault, HttpStatus.OK);
    }

    @GetMapping(value = "/mushrooms/custom/{username}")
    public ResponseEntity<List<MushroomWithConditionsDto>>
    getDefaultAndCustomWithConditions(@PathVariable String username) {
        List<MushroomWithConditionsDto> defaultAndCustom = service.getDefaultMushroomsWithConditions();
        defaultAndCustom.addAll(service.getCustomMushroomsWithConditions(username));
        return new ResponseEntity<>(defaultAndCustom, HttpStatus.OK);
    }

}
