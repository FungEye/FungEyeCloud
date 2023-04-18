package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Mushroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MushroomMapper {

    public static MushroomDto mapToMushroomDto(Mushroom mush)
    {
        MushroomDto dto = new MushroomDto();
        dto.setId(mush.getId());
        dto.setDescription(mush.getDescription());
        dto.setName(mush.getName());
        dto.setGrowId(mush.getGrow().getId());

        return dto;
    }

    public static List<MushroomDto> mapToMushroomDtoList(Set<Mushroom> list)
    {
        List<MushroomDto> dtoList = new ArrayList<>();
        list.forEach(mushroom -> dtoList.add(mapToMushroomDto(mushroom)));

        return dtoList;
    }
}
