package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.enities.users.UserEntity;

public class UserMapper {
    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static UserEntity userFromCreationDto(UserCreationDto dto)
    {
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        return user;
    }
}
