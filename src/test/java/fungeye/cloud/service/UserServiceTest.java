package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.AuthResponseDto;
import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.dtos.UserLoginDto;
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
        dto = null;

    }


    @Test
    void testLogin() {
        String username = "username";
        String password = "password";
        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(user));

        dto = userService.login(new UserLoginDto(username, password));
        assertNotNull(dto);
    }


    @Test
    void testLoginInvalidPassword() {
        String badPassword = "badPassword";
        when(userRepository.findByUsername(user.getUsername())).thenReturn(java.util.Optional.of(user));

        dto = userService.login(new UserLoginDto(user.getUsername(), badPassword));
        assertNotNull(dto);
    }

    @Test
    void testLoginInvalidUser() {
        String username = "username";
        String password = "password";
        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.empty());

        dto = userService.login(new UserLoginDto(username, password));
        System.out.println(dto.toString());
        
    }

}