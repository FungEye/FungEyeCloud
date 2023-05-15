package fungeye.cloud.domain.dtos;

import lombok.Data;

@Data
public class UserCreationDto {
    private String username;
    private String password;

    public UserCreationDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
