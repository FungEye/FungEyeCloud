package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.AuthResponseDto;
import fungeye.cloud.domain.dtos.UserLoginDto;
import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.domain.enities.users.Role;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.RoleRepository;
import fungeye.cloud.persistence.repository.UserRepository;
import fungeye.cloud.security.JwtGenerator;
import fungeye.cloud.service.UserService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserCreationDto dto) {

        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginDto dto){
        return new ResponseEntity<>(userService.login(dto), HttpStatus.OK);
    }


}
