package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.AuthResponseDto;
import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.enities.users.Role;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.domain.exceptions.NotUniqueException;
import fungeye.cloud.persistence.repository.RoleRepository;
import fungeye.cloud.persistence.repository.UserRepository;
import fungeye.cloud.security.JwtGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtGenerator jwtGenerator;

    private UserService userService;


    private UserEntity user;
    private Role role;
    private AuthResponseDto dto;
    private Authentication auth;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository, roleRepository,
                authenticationManager, jwtGenerator);

        user = new UserEntity();
        user.setUsername("username");
        user.setPassword("password");

        role = new Role();
        role.setName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);
        auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

    }

    @Test
    public void testCreateUser_success() throws NotUniqueException {
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        when(roleRepository.findByName(role.getName())).thenReturn(Optional.ofNullable(role));
        when(authenticationManager.authenticate(any())).thenReturn(any(Authentication.class));
        when(jwtGenerator.generateToken(any())).thenReturn(any(String.class));


        assertEquals(userService.createUser(new UserCreationDto("username", "password")), any(AuthResponseDto.class));



        //assertTrue(userService.createUser(new UserCreationDto("username", "password")));
    }

    @Test
    public void testCreateUser_failure() throws NotUniqueException {
        UserCreationDto dto = new UserCreationDto("testuser", "password");
        when(userRepository.save(any(UserEntity.class))).thenReturn(null);

        assertNotEquals(userService.createUser(dto), any(AuthResponseDto.class));
    }

    @Test
    public void testCreateUser_exception() {
        UserCreationDto dto = new UserCreationDto("testuser", "password");
        when(userRepository.save(any(UserEntity.class))).thenThrow(new NotUniqueException("User with username: " + dto.getUsername() + " already exists"));

        assertThrows(NotUniqueException.class, ()-> userService.createUser(dto));
    }

    /*
    @Test
    void testLogin() {
        String username = "username";
        String password = "password";
        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(user));

        boolean isLoggedIn = userService.login(username, password);
        assertTrue(isLoggedIn);
    }

    @Test
    void testLoginInvalidPassword() {
        String badPassword = "badPassword";
        when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.of(user));

        boolean isLoggedIn = userService.login(user.getUsername(), badPassword);
        assertFalse(isLoggedIn);
    }

    @Test
    void testLoginInvalidUser() {
        String username = "username";
        String password = "password";
        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.empty());

        boolean isLoggedIn = userService.login(username, password);
        Assertions.assertFalse(isLoggedIn);
    }

     */
}