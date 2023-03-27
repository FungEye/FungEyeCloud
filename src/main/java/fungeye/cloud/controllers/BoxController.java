package fungeye.cloud.controllers;

import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.persistence.repository.BoxRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //not sure if needed todo
@RestController
@RequestMapping("/api")
public class BoxController {

    private BoxRepository repository;

    public BoxController(BoxRepository repository)
    {
        this.repository = repository;
    }

    @PostMapping("/")
    public Box createBox(@RequestBody Box box)
    {
        return repository.save(box);
    }

    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Box getBoxById(@RequestParam Long id)
    {
        return repository.findById(id).orElseThrow();
    }
}
