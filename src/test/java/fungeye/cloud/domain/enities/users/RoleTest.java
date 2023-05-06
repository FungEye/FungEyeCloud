package fungeye.cloud.domain.enities.users;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import org.mockito.*;

public class RoleTest {
    @Test
    void testEquals() {
        Role role1 = new Role();
        role1.setId(1);
        Role role2 = new Role();
        role2.setId(1);
        Role role3 = new Role();
        role3.setId(2);

        assertTrue(role1.equals(role2));
        assertFalse(role1.equals(role3));
        assertFalse(role1.equals(null));
        assertFalse(role1.equals(new Object()));
    }

    @Test
    void testHashCode() {
        Role role1 = new Role();
        Role role2 = new Role();

        assertEquals(role1.hashCode(), role2.hashCode());
    }
}
