package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void testGettersAndSetters() {
        User user = new User();

        user.setUsername("john");
        assertEquals("john", user.getUsername());

        user.setPassword("pass123ff");
        assertEquals("pass123ff", user.getPassword());

        Box box = new Box();
        ArrayList<Box> boxes = new ArrayList<>();

        user.setBoxes(boxes);
        assertEquals(boxes, user.getBoxes());
    }

}