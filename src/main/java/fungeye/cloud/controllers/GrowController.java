package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.GrowCreationDto;
import fungeye.cloud.domain.dtos.GrowDto;
import fungeye.cloud.domain.dtos.GrowIdDto;
import fungeye.cloud.domain.dtos.GrowUpdateDto;
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

    @PostMapping("/grow")
    public ResponseEntity<GrowDto> createGrow(GrowCreationDto dto)
    {
        return new ResponseEntity<>(service.createGrow(dto), HttpStatus.OK);
    }

    @GetMapping("/grow/all/empty")
    public ResponseEntity<List<GrowIdDto>>getGrowsByUsername (String username)
    {
        return new ResponseEntity<>(service.getAllGrowsByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<GrowDto> updateGrow(GrowUpdateDto dto)
    {
        return new ResponseEntity<>(service.updateGrow(dto), HttpStatus.OK);
    }


}