package fungeye.cloud.domain.enities.users;

import fungeye.cloud.domain.enities.Box;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {
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