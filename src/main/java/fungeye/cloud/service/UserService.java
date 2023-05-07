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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static fungeye.cloud.service.mappers.UserMapper.userFromCreationDto;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtGenerator jwtGenerator;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       AuthenticationManager authenticationManager,
                       JwtGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    public UserLoginDto createUser(UserCreationDto dto) throws NotUniqueException {

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new NotUniqueException("User with username: " + dto.getUsername() + " already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singleton(roles));

        UserEntity created = userRepository.save(user);
        return new UserLoginDto(created.getUsername(), created.getPassword());

    }

    public AuthResponseDto login(UserLoginDto dto) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(),
                        dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtGenerator.generateToken(auth);
        return new AuthResponseDto(token);
    }
}
