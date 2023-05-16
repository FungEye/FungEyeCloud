package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.GrowCreationDto;
import fungeye.cloud.domain.dtos.GrowDto;
import fungeye.cloud.domain.dtos.GrowIdDto;
import fungeye.cloud.domain.dtos.GrowUpdateDto;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.persistence.repository.GrowRepository;
import fungeye.cloud.service.mappers.GrowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GrowService {

    private GrowRepository repository;
    public GrowDto createGrow(GrowCreationDto dto)
    {
        Grow toCreate = GrowMapper.mapFromCreationDto(dto);
        Grow created = repository.save(toCreate);
        return GrowMapper.mapToGrowDto(toCreate);
    }

    public List<GrowIdDto> getAllGrowsByUsername(String username)
    {
        List<Grow> grows = repository.findGrowsByUserEntity_Username(username);
        List<GrowIdDto> dtoList = new ArrayList<>();

        for (Grow grow : grows)
        {
            dtoList.add(GrowMapper.mapToGrowIdDto(grow));
        }

        return dtoList;
    }

    public GrowDto updateGrow(GrowUpdateDto dto)
    {
        Grow toUpdated = repository.findById(dto.getId()).orElseThrow();

        if(toUpdated.getDevelopmentStage().equals(""))
        {
            //todo is there specific exception to use
            throw new IllegalArgumentException("Please fill all required sections");
        }
        else
        {
            toUpdated.setIsActive(dto.getIsActive());
            toUpdated.setDevelopmentStage(dto.getDevelopStage());
        }
        return GrowMapper.mapToGrowDto(toUpdated);

    }
}