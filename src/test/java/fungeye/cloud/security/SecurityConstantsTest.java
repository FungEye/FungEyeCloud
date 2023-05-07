package fungeye.cloud.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static fungeye.cloud.security.SecurityConstants.JWT_EXPIRATION;
import static fungeye.cloud.security.SecurityConstants.JWT_SECRET;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SecurityConstantsTest {

    @Test
    void testConstantValues() {
        assertEquals("secret", JWT_SECRET);
        assertEquals(900000, JWT_EXPIRATION);
    }

}