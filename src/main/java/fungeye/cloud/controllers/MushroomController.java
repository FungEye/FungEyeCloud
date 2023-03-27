package fungeye.cloud.controllers;

import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.persistence.repository.MushroomRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // not sure if we needed todo
@RestController
@RequestMapping("/api")
public class MushroomController {

    private MushroomRepository repository;

    public MushroomController(MushroomRepository repository)
    {this.repository = repository;}

    @PostMapping("/")
    public Mushroom createMushroom(@RequestBody Mushroom mushroon)
    {
        return repository.save(mushroon);
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mushroom getMushroomById(@RequestParam Long id)
    {
        return repository.findById(id).orElseThrow();
    }
    {}
}
