package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.GrowCreationDto;
import fungeye.cloud.domain.dtos.GrowDto;
import fungeye.cloud.domain.dtos.GrowIdDto;
import fungeye.cloud.domain.enities.Grow;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static fungeye.cloud.service.mappers.DateTimeMapper.mapToDateDto;
import static fungeye.cloud.service.mappers.DateTimeMapper.mapToInstant;

public class GrowMapper {
    private GrowMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Grow mapFromCreationDto(GrowCreationDto dto)
    {
        Grow grow = new Grow();
        grow.setDateStarted(LocalDate.of(
                dto.getDate().getYear(),
                dto.getDate().getMonth(),
                dto.getDate().getDay()));
        grow.setIsActive(grow.getIsActive());
        grow.setDevelopmentStage(dto.getDevelopStage());
        // todo the grow should have box and mushrooms ids and not objects.
        grow.setBox(dto.getBoxId());
        grow.setMushroom(dto.getMushroomId());

        return grow;
    }
    public static GrowDto mapToGrowDto (Grow grow)
    {
        GrowDto dto = new GrowDto();
        dto.setId(grow.getId());
        dto.setActive(grow.getIsActive());
        dto.setDate(mapToDateDto(grow.getDateStarted()));
        dto.setStage(grow.getDevelopmentStage());
        dto.setBoxId(grow.getBox().getId());

        return dto;
    }

    public static List<GrowDto> mapToGrowDtoList(Set<Grow> grows) {
        List<GrowDto> list = new ArrayList<>();
        grows.forEach(g -> list.add(mapToGrowDto(g)));

        return list;
    }

    public static GrowIdDto mapToGrowIdDto(Grow grow)
    {
        GrowIdDto dto = new GrowIdDto();
        dto.setId(grow.getId());

        return dto;
    }
}
