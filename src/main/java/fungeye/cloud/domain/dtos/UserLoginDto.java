package fungeye.cloud.domain.dtos;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.users.UserEntity} entity
 */
@Data
public class UserLoginDto implements Serializable {
    private final String username;
    private final String password;
}