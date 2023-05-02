package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.enities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    void userFromCreationDto() {
        UserCreationDto userCreationDto = new UserCreationDto("john", "pass123ff");
        User user = UserMapper.userFromCreationDto(userCreationDto);
        assertEquals("john", user.getUsername());
    }
}