package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.BoxDto;
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

    @PostMapping("/box")
    public Box createBox(@RequestBody BoxDto dto)
    {
        Box box = new Box();
        box.setId(dto.getId());
        return repository.save(box);
    }

    @GetMapping(value = "/box/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Box getBoxById(@RequestParam Long id)
    {
        return repository.findById(id).orElseThrow();
    }
}
