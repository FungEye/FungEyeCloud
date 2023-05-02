package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.enities.User;

public class UserMapper {

    public static User userFromCreationDto(UserCreationDto dto)
    {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
