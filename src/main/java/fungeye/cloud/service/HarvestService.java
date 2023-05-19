package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.HarvestCreationDto;
import fungeye.cloud.domain.dtos.HarvestDetailsDto;
import fungeye.cloud.domain.enities.Harvest;
import fungeye.cloud.persistence.repository.HarvestRepository;
import fungeye.cloud.service.mappers.HarvestMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HarvestService {

    private HarvestRepository repository;
    private HarvestMapper mapper;

    public HarvestService(HarvestRepository repository, HarvestMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public HarvestDetailsDto addHarvest(HarvestCreationDto dto) {
        Harvest toCreate = mapper.mapCreationDtoToEntity(dto);
        Harvest created = repository.save(toCreate);
        return mapper.mapEntityToDetailsDto(created);
    }

    public List<HarvestDetailsDto> getAllHarvestsByUsername(String username) {
        List<Harvest> response = repository.findByGrow_Box_UserEntity_UsernameOrderByDateHarvestedDesc(username);
        if (response.isEmpty()) {
            throw new NoSuchElementException(String.format("#%s has no harvests.", username));
        }
        List<HarvestDetailsDto> result = new ArrayList<>();
        for (Harvest h: response
             ) {
            result.add(mapper.mapEntityToDetailsDto(h));
        }

        return result;
    }

    public List<HarvestDetailsDto> getAllHarvestsByGrowId(Long growId) {
        List<Harvest> response = repository.findByGrow_IdOrderByDateHarvestedDesc(growId);
        if (response.isEmpty()) {
            throw new NoSuchElementException(String.format("Grow #%d has no harvests.", growId));
        }
        List<HarvestDetailsDto> result = new ArrayList<>();
        for (Harvest h: response
        ) {
            result.add(mapper.mapEntityToDetailsDto(h));
        }
        return result;
    }
}
