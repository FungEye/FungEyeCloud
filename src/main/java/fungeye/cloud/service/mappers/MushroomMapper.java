package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.CustomMushroomCreationDto;
import fungeye.cloud.domain.dtos.DefaultMushroomCreationDto;
import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;

import java.util.ArrayList;
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
        dto.setUserId(mush.getUser().getId());

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
}
