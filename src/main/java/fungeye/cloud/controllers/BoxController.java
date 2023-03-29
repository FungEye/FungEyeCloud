package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.persistence.repository.BoxRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //not sure if needed todo
@RestController
@RequestMapping("/")
public class BoxController {

    private BoxRepository repository;

    public BoxController(BoxRepository repository)
    {
        this.repository = repository;
    }

    @PostMapping("/box")
    public ResponseEntity<BoxDto> createBox()
    {
        Box box = repository.save(new Box());
        BoxDto boxdto = new BoxDto();
        boxdto.setId(box.getId());

        return new ResponseEntity<>(boxdto, HttpStatus.CREATED);
    }

    @GetMapping(value = "/box{id}")
    public ResponseEntity<BoxDetailsDto> getBoxById(@PathVariable Long id)
    {
        Box box = repository.findById(id).orElseThrow();
        BoxDetailsDto boxDto = new BoxDetailsDto();
        //todo this method is not finished

        return new ResponseEntity<>(boxDto, HttpStatus.OK);
    }
}
