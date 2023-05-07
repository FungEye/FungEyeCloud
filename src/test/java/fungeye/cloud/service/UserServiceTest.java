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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtGenerator jwtGenerator;

    private UserService service;

    private UserEntity user;
    private UserCreationDto createDto;
    private UserLoginDto loginDto;
    private AuthResponseDto authDto;
    private Role userRole;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new UserService(userRepository, roleRepository, authenticationManager, jwtGenerator);
        user = new UserEntity();
        user.setUsername("username");
        user.setPassword("password");
        userRole = new Role();
        userRole.setName("USER");

        createDto = null;
        loginDto = null;
        authDto = new AuthResponseDto("ACCESSTOKEN");
    }

    @Test
    void createUser_success() throws NotUniqueException {
        createDto = new UserCreationDto(user.getUsername(), user.getPassword());
        loginDto = new UserLoginDto(user.getUsername(), user.getPassword());
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(userRole));

        assertEquals(loginDto, service.createUser(createDto));
    }

    @Test
    void createUser_failure() {
        createDto = new UserCreationDto(user.getUsername(), user.getPassword());
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        assertThrows(NotUniqueException.class, () -> service.createUser(createDto));
    }

    @Test
    void login_success() {
        loginDto = new UserLoginDto(user.getUsername(), user.getPassword());
        when(jwtGenerator.generateToken(any())).thenReturn(authDto.getAccessToken());

        assertEquals(service.login(loginDto), authDto);
    }

    @Test
    void login_failed() {
        loginDto = new UserLoginDto(user.getUsername(), "wrongpassword");
        when(authenticationManager.authenticate(any(Authentication.class))).thenThrow(BadCredentialsException.class);

        assertThrows(BadCredentialsException.class, () -> service.login(loginDto));
    }
}