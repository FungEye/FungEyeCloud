package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.domain.dtos.SimpleBoxGrowDto;
import fungeye.cloud.domain.enities.Box;

import java.util.ArrayList;
import java.util.List;

import static fungeye.cloud.service.mappers.GrowMapper.mapToGrowDtoList;
import static fungeye.cloud.service.mappers.MeasuredConditionsMapper.mapToDtoList;

public class BoxMapper {

    private BoxMapper() {
        throw new IllegalStateException("Utility class");
    }

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

    public static Box mapFromBoxDto(BoxDetailsDto dto) {
        Box box = new Box();
        box.setId(dto.getId());
        return box;
    }

    public static List<BoxDetailsDto> mapToBoxDtoList(List<Box> boxes){
        List<BoxDetailsDto> list = new ArrayList<>();
        boxes.forEach(box -> list.add(mapToBoxDto(box)));

        return list;
    }

    public static SimpleBoxGrowDto mapToSimpleBoxGrowDto(Box box) {
        SimpleBoxGrowDto dto = new SimpleBoxGrowDto();
        dto.setId(box.getId());
        dto.setSimpleGrowDtos(GrowMapper.mapToGrowIdMushroomNameDtoList(box.getGrows()));

        return dto;
    }

    public static Box mapFromSimpleBoxGrowDto(SimpleBoxGrowDto dto) {
        Box box = new Box();
        box.setId(dto.getId());
        box.setGrows(GrowMapper.mapFromGrowIdMushroomNameDtoList(dto.getSimpleGrowDtos()));

        return box;
    }

    public static List<SimpleBoxGrowDto> mapToSimpleBoxGrowDtoList(List<Box> boxes) {
        List<SimpleBoxGrowDto> dtos = new ArrayList<>();
        boxes.forEach(b -> dtos.add(mapToSimpleBoxGrowDto(b)));

        return dtos;
    }

    public static List<Box> mapFromSimpleBoxGrowDtoList(List<SimpleBoxGrowDto> dtos) {
        List<Box> list = new ArrayList<>();
        dtos.forEach(d -> list.add(mapFromSimpleBoxGrowDto(d)));

        return list;
    }
}
