package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.auth.AuthResponseDto;
import fungeye.cloud.domain.dtos.user.UserCreationDto;
import fungeye.cloud.domain.dtos.user.UserLoginDto;
import fungeye.cloud.service.UserService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody UserCreationDto dto) {
        UserLoginDto login = userService.createUser(dto);
        return new ResponseEntity<>(userService.login(login), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody UserLoginDto dto){
        return new ResponseEntity<>(userService.login(dto), HttpStatus.OK);
    }
}
