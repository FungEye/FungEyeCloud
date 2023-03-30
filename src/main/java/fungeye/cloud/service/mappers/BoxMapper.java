package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.enities.Box;

import static fungeye.cloud.service.mappers.GrowMapper.mapToGrowDtoList;
import static fungeye.cloud.service.mappers.MeasuredConditionsMapper.mapToDtoList;

public class BoxMapper {

    public static BoxDto mapToSimpleDto(Box box)
    {
        BoxDto dto = new BoxDto();
        dto.setId(box.getId());
        return dto;
    }

    public static BoxDetailsDto mapToBoxDto(Box box)
    {
        BoxDetailsDto dto = new BoxDetailsDto();
        dto.setId(box.getId());
        dto.setGrows(mapToGrowDtoList(box.getGrows()));
        dto.setConditions(mapToDtoList(box.getMeasuredConditions()));

        return dto;
    }
}
