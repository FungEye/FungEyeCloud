package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.HarvestCreationDto;
import fungeye.cloud.domain.dtos.HarvestDetailsDto;
import fungeye.cloud.domain.dtos.SimpleDateDto;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Harvest;
import fungeye.cloud.persistence.repository.GrowRepository;
import fungeye.cloud.persistence.repository.MushroomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class HarvestMapper {

    private MushroomRepository mushroomRepository;
    private GrowRepository growRepository;

    public HarvestMapper(MushroomRepository mushroomRepository, GrowRepository growRepository) {
        this.mushroomRepository = mushroomRepository;
        this.growRepository = growRepository;
    }

    public Harvest mapCreationDtoToEntity(HarvestCreationDto dto) {
        Harvest toCreate = new Harvest();
        toCreate.setGrow(growRepository.getReferenceById(dto.getGrowId()));
        Grow grow = growRepository.findById(dto.getGrowId()).orElseThrow();
        toCreate.setMushroom(mushroomRepository.getReferenceById(grow.getMushroom().getId()));
        toCreate.setDateHarvested(LocalDate.of(
                dto.getHarvestDate().getYear(),
                dto.getHarvestDate().getMonth(),
                dto.getHarvestDate().getDay()));
        toCreate.setWeight(dto.getWeight());

        return toCreate;
    }

    public HarvestDetailsDto mapEntityToDetailsDto(Harvest harvest) {
        HarvestDetailsDto details = new HarvestDetailsDto();
        details.setId(harvest.getId());
        details.setMushroomName(harvest.getMushroom().getName());
        details.setHarvestDate(
                new SimpleDateDto(harvest.getDateHarvested().getYear(),
                        harvest.getDateHarvested().getMonthValue(),
                        harvest.getDateHarvested().getDayOfMonth())
        );
        details.setGrowId(harvest.getGrow().getId());
        details.setWeight(harvest.getWeight());
        return details;
    }
}
