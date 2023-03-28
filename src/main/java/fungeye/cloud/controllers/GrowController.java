package fungeye.cloud.controllers;

import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.persistence.repository.GrowRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // not sure if we needed todo
@RestController
@RequestMapping("/api")
public class GrowController {

    private GrowRepository repository;

    @PostMapping("/grow")
    public Grow createGrow(@RequestBody Grow grow)
    {
        return repository.save(grow);
    }

    @GetMapping(value = "/grow/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Grow getGrowById(@RequestParam Long id)
    {
        return repository.findById(id).orElseThrow();
    }

}
