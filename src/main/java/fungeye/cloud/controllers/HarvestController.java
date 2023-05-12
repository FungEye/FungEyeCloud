package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.HarvestCreationDto;
import fungeye.cloud.domain.dtos.HarvestDetailsDto;
import fungeye.cloud.domain.dtos.SimpleDateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/harvest")
public class HarvestController {

    @PostMapping("/")
    public ResponseEntity<HarvestDetailsDto> createHarvest(@RequestBody HarvestCreationDto dto) {

        // TODO Move to service and actually process information
        HarvestDetailsDto response = new HarvestDetailsDto();
        response.setId(1L);
        response.setHarvestDate(dto.getHarvestDate());
        response.setMushroomName("Dummy Data for creating a harvest");
        response.setWeight(2.4);
        response.setGrowId(dto.getGrowId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<HarvestDetailsDto>> getAllHarvestsByUserId(@PathVariable(name = "userId") long userId) {

        // TODO Move to service class and do stuff
        List<HarvestDetailsDto> response = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HarvestDetailsDto dto = new HarvestDetailsDto();
            dto.setId((long) i + 2);
            dto.setHarvestDate(new SimpleDateDto(2023, 5, 12));
            dto.setMushroomName(String.format("Dummy harvest # %d", i +1 ));
            dto.setWeight((i + 1) * 1.08);
            dto.setGrowId((long)i);
            response.add(dto);
        }

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


}
