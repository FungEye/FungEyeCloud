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

    public List<HarvestDetailsDto> getAllHarvestsByUserId(int userId) {
        List<Harvest> response = repository.findByGrow_Box_UserEntity_Id(userId);
        if (response.isEmpty()) {
            throw new NoSuchElementException(String.format("User with ID#%d has no harvests.", userId));
        }
        List<HarvestDetailsDto> result = new ArrayList<>();
        for (Harvest h: response
             ) {
            result.add(mapper.mapEntityToDetailsDto(h));
        }

        return result;
    }



}