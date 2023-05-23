package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.user.UserCreationDto;
import fungeye.cloud.domain.enities.users.UserEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserMapperTest {

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<UserMapper> constructor = UserMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void userFromCreationDto() {
        UserCreationDto userCreationDto = new UserCreationDto("john", "pass123ff");
        UserEntity user = UserMapper.userFromCreationDto(userCreationDto);
        assertEquals("john", user.getUsername());
    }
}