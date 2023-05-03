package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.enities.User;
import fungeye.cloud.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static fungeye.cloud.service.mappers.UserMapper.userFromCreationDto;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;
    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);

        user = new User();
        user.setUsername("username");
        user.setPassword("password");
    }

    @Test
    public void testCreateUser_success() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        assertTrue(userService.createUser(new UserCreationDto("username", "password")));
    }

    @Test
    public void testCreateUser_failure() {
        UserCreationDto dto = new UserCreationDto("testuser", "password");
        when(userRepository.save(any(User.class))).thenReturn(null);

        assertFalse(userService.createUser(dto));
    }

    @Test
    public void testCreateUser_exception() {
        UserCreationDto dto = new UserCreationDto("testuser", "password");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

        assertFalse(userService.createUser(dto));
    }

    @Test
    void testLogin() {
        String username = "username";
        String password = "password";
        when(userRepository.findById(username)).thenReturn(java.util.Optional.of(user));

        boolean isLoggedIn = userService.login(username, password);
        assertTrue(isLoggedIn);
    }

    @Test
    void testLoginInvalidPassword() {
        String badPassword = "badPassword";
        when(userRepository.findById(user.getUsername())).thenReturn(java.util.Optional.of(user));

        boolean isLoggedIn = userService.login(user.getUsername(), badPassword);
        assertFalse(isLoggedIn);
    }

    @Test
    void testLoginInvalidUser() {
        String username = "username";
        String password = "password";
        when(userRepository.findById(username)).thenReturn(java.util.Optional.empty());

        boolean isLoggedIn = userService.login(username, password);
        Assertions.assertFalse(isLoggedIn);
    }
}