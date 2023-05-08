package fungeye.cloud.security;

import fungeye.cloud.domain.enities.users.Role;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserEntity user;
    private UserEntity admin;
    private Role userRole;
    private Role adminRole;
    private HashSet<Role> roles;
    private CustomUserDetailsService test;
    private User response;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new UserEntity();
        user.setUsername("user");
        user.setPassword("password");
        admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword("password");
        roles = new HashSet<>();
        userRole = new Role();
        userRole.setName("USER");
        adminRole = new Role();
        adminRole.setName("ADMIN");
        test = new CustomUserDetailsService(userRepository);
    }

    @Test
    void loadUserByUsername_success() {
        roles.add(userRole);
        response = new User("user", "password", roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet()));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        assertEquals(test.loadUserByUsername("user"), response);
    }

    @Test
    void loadAdminByUsername_success() {
        roles.add(adminRole);
        response = new User("admin", "password", roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet()));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(admin));
        assertEquals(test.loadUserByUsername("admin"), response);
    }

    @Test
    void loadCombinationByUsername_success() {
        roles.add(adminRole);
        roles.add(userRole);
        response = new User("admin", "password", roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet()));
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(admin));
        assertEquals(test.loadUserByUsername("admin"), response);
    }

    @Test
    void loadByUsername_NotFound() {
        when(userRepository.findByUsername("notfound")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> test.loadUserByUsername("notfound"));
    }

}