package fungeye.cloud.domain.enities;

import fungeye.cloud.domain.enities.users.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void testGettersAndSetters() {
        UserEntity user = new UserEntity();

        user.setUsername("john");
        assertEquals("john", user.getUsername());

        user.setPassword("pass123ff");
        assertEquals("pass123ff", user.getPassword());

        Box box = new Box();
        Set<Box> boxes = new HashSet<>();

        user.setBoxes(boxes);
        assertEquals(boxes, user.getBoxes());
    }

}