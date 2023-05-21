package fungeye.cloud.security;

public class SecurityConstants {

    private SecurityConstants() {
        throw new IllegalStateException("Utility class should not be instantiated!");
    }

    public static final long JWT_EXPIRATION = 10800000; // Token expires after 3 hours
    public static final String JWT_SECRET = "secret";
}
