package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.grow.GrowCreationDto;
import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.grow.GrowIdMushroomNameDto;
import fungeye.cloud.domain.dtos.grow.GrowUpdateDto;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.persistence.repository.GrowRepository;
import fungeye.cloud.service.mappers.GrowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrowService {

    private GrowRepository repository;

    public GrowService(GrowRepository repository) {
        this.repository = repository;
    }

    public GrowDto createGrow(GrowCreationDto dto)
    {
        Grow toCreate = GrowMapper.mapFromCreationDto(dto);
        Grow created = repository.save(toCreate);
        created.setIsActive(true);
        return GrowMapper.mapToGrowDto(created);
    }

    public List<GrowIdMushroomNameDto> getAllGrowsByUsername(String username)
    {
        List<Grow> grows = repository.findGrowsByBox_UserEntity_Username(username);
        List<GrowIdMushroomNameDto> dtoList = new ArrayList<>();

        for (Grow grow : grows)
        {
            dtoList.add(GrowMapper.mapGrowIdWithMushroomIdDto(grow));
        }

        return dtoList;
    }

    public GrowDto updateGrow(GrowUpdateDto dto)
    {
        Grow toUpdated = repository.findById(dto.getId()).orElseThrow();

        if(toUpdated.getDevelopmentStage().isBlank())
        {
            throw new IllegalArgumentException("Please fill all required sections");
        }
        else
        {
            toUpdated.setIsActive(dto.getIsActive());
            toUpdated.setDevelopmentStage(dto.getDevelopStage());
        }
        return GrowMapper.mapToGrowDto(toUpdated);

    }

    public GrowDto endGrow (Long id)
    {
        Grow toEnd = repository.findById(id).orElseThrow();

        if (toEnd.getIsActive().equals(true))
        {
            toEnd.setIsActive(false);
            repository.save(toEnd);
        }
        else
        {
            throw new IllegalArgumentException("The Grow is already not active");
        }

        return GrowMapper.mapToGrowDto(toEnd);
    }
}
