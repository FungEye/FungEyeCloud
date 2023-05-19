package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.*;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MushroomMapper {
    private MushroomMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static MushroomDto mapToMushroomDto(Mushroom mush)
    {
        MushroomDto dto = new MushroomDto();
        dto.setId(mush.getId());
        dto.setDescription(mush.getDescription());
        dto.setName(mush.getName());
        dto.setOrigin(mush.getOrigin());
        dto.setImageUrl(mush.getImageUrl());

        return dto;
    }

    public static Mushroom mapCreateToMushroom(MushroomCreationDTO dto) {
        Mushroom mushroom = new Mushroom();
        mushroom.setName(dto.getName());
        mushroom.setDescription(dto.getDescription());
        mushroom.setOrigin(dto.getOrigin());

        // Create a user with only an id
        UserEntity user = new UserEntity();
        user.setId(dto.getUserId());
        mushroom.setUser(user);

        return mushroom;
    }

    public static Mushroom mapDefaultCreateToMushroom(DefaultMushroomCreationDto dto) {
        Mushroom mushroom = new Mushroom();
        mushroom.setName(dto.getName());
        mushroom.setDescription(dto.getDescription());
        mushroom.setOrigin(dto.getOrigin());
        mushroom.setImageUrl(dto.getImageUrl());

        // Create a user with only an id
        UserEntity user = new UserEntity();
        // Set to three for the admin
        user.setId(3);
        mushroom.setUser(user);

        return mushroom;
    }

    public static Mushroom mapCustomCreateToMushroom(CustomMushroomCreationDto dto) {
        Mushroom mushroom = new Mushroom();
        mushroom.setName(dto.getName());
        mushroom.setDescription(dto.getDescription());
        mushroom.setOrigin(dto.getOrigin());
        mushroom.setImageUrl(dto.getImageUrl());

        // Create a user with only an id
        UserEntity user = new UserEntity();
        // Set to three for the admin
        user.setUsername(dto.getUsername());
        mushroom.setUser(user);

        return mushroom;
    }

    public static List<MushroomDto> mapToMushroomDtoList(Set<Mushroom> list)
    {
        List<MushroomDto> dtoList = new ArrayList<>();
        list.forEach(mushroom -> dtoList.add(mapToMushroomDto(mushroom)));

        return dtoList;
    }

    // todo might not be needed, but maybe
    public static MushroomDto mapUpdateMushroomDto(MushroomUpdateDto dto)
    {
        MushroomDto mushroomDto = new MushroomDto();
        mushroomDto.setId(dto.getId());
        mushroomDto.setDescription(dto.getDescription());
        mushroomDto.setName(dto.getName());
        mushroomDto.setImageUrl(dto.getImageUrl());

        return mushroomDto;
    }

    public static Mushroom mapFromUpdateMushroomDto(MushroomUpdateDto dto)
    {
        Mushroom mush = new Mushroom();
        mush.setId(dto.getId());
        mush.setName(dto.getName());
        mush.setDescription(dto.getDescription());
        mush.setImageUrl(dto.getImageUrl());

        Set<IdealCondition> list = new HashSet<>(dto.getIdealConditions());
        mush.setIdealConditions(list);

        return mush;
    }

    public static MushroomWithConditionsDto mapToMushroomWithConditionsDto(Mushroom mushroom) {
        MushroomWithConditionsDto dto = new MushroomWithConditionsDto();
        dto.setId(mushroom.getId());
        dto.setDescription(mushroom.getDescription());
        dto.setName(mushroom.getName());
        dto.setOrigin(mushroom.getOrigin());
        dto.setImageUrl(mushroom.getImageUrl());

        return dto;
    }

    public static Mushroom mapFromMushroomWithConditionsDto(MushroomWithConditionsDto dto) {
        Mushroom mushroom = new Mushroom();
        mushroom.setName(dto.getName());
        mushroom.setDescription(dto.getDescription());
        mushroom.setOrigin(dto.getOrigin());
        mushroom.setImageUrl(dto.getImageUrl());

        // Create a user with only an id
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        mushroom.setUser(user);

        return mushroom;
    }
}
