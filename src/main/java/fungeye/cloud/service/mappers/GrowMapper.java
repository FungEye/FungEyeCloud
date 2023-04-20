package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.GrowDto;
import fungeye.cloud.domain.enities.Grow;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static fungeye.cloud.service.mappers.DateTimeMapper.mapToDateDto;
import static fungeye.cloud.service.mappers.MushroomMapper.mapToMushroomDtoList;

public class GrowMapper {

    public static GrowDto mapToGrowDto (Grow grow)
    {
        GrowDto dto = new GrowDto();
        dto.setId(grow.getId());
        dto.setMushroomDtoList(mapToMushroomDtoList(grow.getMushrooms()));
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
}
