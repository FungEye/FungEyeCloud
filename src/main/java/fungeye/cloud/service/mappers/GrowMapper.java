package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.grow.GrowCreationDto;
import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.grow.GrowIdMushroomNameDto;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Mushroom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static fungeye.cloud.service.mappers.DateTimeMapper.mapToDateDto;

public class GrowMapper {
    private GrowMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Grow mapFromCreationDto(GrowCreationDto dto) {
        Grow grow = new Grow();
        grow.setDateStarted(LocalDate.of(
                dto.getDate().getYear(),
                dto.getDate().getMonth(),
                dto.getDate().getDay()));
        grow.setIsActive(grow.getIsActive());
        grow.setDevelopmentStage(dto.getDevelopStage());

        Box box = new Box();
        box.setId(dto.getBoxId());
        grow.setBox(box);

        Mushroom mushroom = new Mushroom();
        mushroom.setId(dto.getMushroomId());
        grow.setMushroom(mushroom);

        return grow;
    }

    public static Grow mapFromDto(GrowDto dto) {
        Grow grow = new Grow();
        if (dto.getId() != null) {
            grow.setId(dto.getId());
        }
        grow.setDateStarted(DateTimeMapper.mapFromDateDto(dto.getDate()));
        grow.setIsActive(dto.isActive());
        grow.setDevelopmentStage(dto.getStage());

        Box box = new Box();
        box.setId(dto.getBoxId());
        grow.setBox(box);


        Mushroom mushroom = new Mushroom();
        if (dto.getMushroomId() != null) {
            mushroom.setId(dto.getMushroomId());
            grow.setMushroom(mushroom);
        }

        return grow;
    }

    public static GrowIdMushroomNameDto mapGrowIdWithMushroomIdDto(Grow grow) {
        GrowIdMushroomNameDto dto = new GrowIdMushroomNameDto();
        dto.setId(grow.getId());
        dto.setMushroomId(grow.getId());

        return dto;
    }

    public static GrowDto mapToGrowDto(Grow grow) {
        GrowDto dto = new GrowDto();
        dto.setId(grow.getId());
        dto.setActive(grow.getIsActive());
        dto.setDate(mapToDateDto(grow.getDateStarted()));
        dto.setStage(grow.getDevelopmentStage());
        dto.setBoxId(grow.getBox().getId());
        dto.setMushroomId(grow.getMushroom().getId());

        return dto;
    }

    public static List<GrowDto> mapToGrowDtoList(Set<Grow> grows) {
        List<GrowDto> list = new ArrayList<>();
        grows.forEach(g -> list.add(mapToGrowDto(g)));

        return list;
    }

    public static List<Grow> mapFromGrowDtoList(List<GrowDto> dtos) {
        List<Grow> list = new ArrayList<>();
        dtos.forEach(d -> list.add(mapFromDto(d)));

        return list;
    }


    public static GrowIdMushroomNameDto mapToGrowIdMushroomNameDto(Grow grow) {
        GrowIdMushroomNameDto dto = new GrowIdMushroomNameDto();
        dto.setId(grow.getId());
        dto.setMushroomName(grow.getMushroom().getName());

        return dto;
    }

    public static Grow mapFromGrowIdMushroomNameDto(GrowIdMushroomNameDto dto) {
        Grow grow = new Grow();
        grow.setId(dto.getId());

        Mushroom mushroom = new Mushroom();
        mushroom.setName(dto.getMushroomName());
        grow.setMushroom(mushroom);

        return grow;
    }

    public static List<GrowIdMushroomNameDto> mapToGrowIdMushroomNameDtoList(Set<Grow> set) {
        List<GrowIdMushroomNameDto> dtos = new ArrayList<>();
        set.forEach(g -> dtos.add(mapToGrowIdMushroomNameDto(g)));

        return dtos;
    }

    public static Set<Grow> mapFromGrowIdMushroomNameDtoList(List<GrowIdMushroomNameDto> dtos) {
        Set<Grow> set = new HashSet<>();
        dtos.forEach(d -> set.add(mapFromGrowIdMushroomNameDto(d)));

        return set;
    }
}
