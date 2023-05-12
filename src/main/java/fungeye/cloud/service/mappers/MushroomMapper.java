package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.dtos.UserCreationDto;
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

    public static List<MushroomDto> mapToMushroomDtoList(Set<Mushroom> list)
    {
        List<MushroomDto> dtoList = new ArrayList<>();
        list.forEach(mushroom -> dtoList.add(mapToMushroomDto(mushroom)));

        return dtoList;
    }
}
