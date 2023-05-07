package fungeye.cloud.domain.enities.users;

import fungeye.cloud.domain.enities.Box;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityTest {
    @Test
    void testGettersAndSetters() {
        UserEntity user = new UserEntity();

        user.setId(1);
        assertEquals(1, user.getId());

        user.setUsername("john");
        assertEquals("john", user.getUsername());

        user.setPassword("pass123ff");
        assertEquals("pass123ff", user.getPassword());

        Box box = new Box();
        Set<Box> boxes = new HashSet<>();

        Set<Role> roles = new HashSet<>();
        Role role1 = new Role();
        role1.setId(1);
        role1.setName("bean");
        roles.add(role1);
        user.setRoles(roles);
        Set<Role> retrievedRole = user.getRoles();
        assertEquals(retrievedRole.toArray()[0], role1);

        user.setBoxes(boxes);
        assertEquals(boxes, user.getBoxes());
    }

}