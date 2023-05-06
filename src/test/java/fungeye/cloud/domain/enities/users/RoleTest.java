package fungeye.cloud.domain.enities.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {
    @Test
    void testEquals() {
        Role role1 = new Role();
        role1.setId(1);
        Role role2 = new Role();
        role2.setId(1);
        Role role3 = new Role();
        role3.setId(2);

        assertEquals(role1, role2);
        assertNotEquals(role1, role3);
        assertNotEquals(null, role1);
        assertNotEquals(role1, new Object());
    }

    @Test
    void testHashCode() {
        Role role1 = new Role();
        Role role2 = new Role();

        assertEquals(role1.hashCode(), role2.hashCode());
    }
}
