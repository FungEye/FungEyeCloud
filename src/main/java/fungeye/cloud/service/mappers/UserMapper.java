package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.enities.User;

public class UserMapper {

    public static User userFromCreationDto(UserCreationDto dto)
    {
        return new User(dto.getUsername(), dto.getPassword());
    }
}
