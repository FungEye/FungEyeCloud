package fungeye.cloud.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static fungeye.cloud.security.SecurityConstants.JWT_EXPIRATION;
import static fungeye.cloud.security.SecurityConstants.JWT_SECRET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@ExtendWith(MockitoExtension.class)
class SecurityConstantsTest {

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<SecurityConstants> constructor = SecurityConstants.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testConstantValues() {
        assertEquals("secret", JWT_SECRET);
        //assertEquals(900000, JWT_EXPIRATION);
        assertEquals(10800000, JWT_EXPIRATION);
    }

}