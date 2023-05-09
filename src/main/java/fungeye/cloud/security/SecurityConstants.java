package fungeye.cloud.security;

public class SecurityConstants {

    private SecurityConstants() {
        throw new IllegalStateException("Utility class should not be instantiated!");
    }

    public static final long JWT_EXPIRATION = 900000; // Token expires after 15 minutes?
    public static final String JWT_SECRET = "secret";
}
