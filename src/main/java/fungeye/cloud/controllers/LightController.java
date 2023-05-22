package fungeye.cloud.controllers;

import fungeye.cloud.service.LightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class LightController {
    private LightService service;

    public LightController(LightService service) {
        this.service = service;
    }

    @PostMapping("{boxId}/light")
    public ResponseEntity<Boolean> toggleLight(@PathVariable int boxId) {
        return new ResponseEntity<>(service.toggleLight(boxId), HttpStatus.OK);
    }
}
