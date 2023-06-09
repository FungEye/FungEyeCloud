package fungeye.cloud.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fungeye.cloud.domain.dtos.auth.AuthResponseDto;
import fungeye.cloud.domain.dtos.user.UserCreationDto;
import fungeye.cloud.domain.dtos.user.UserLoginDto;
import fungeye.cloud.domain.exceptions.NotUniqueException;
import fungeye.cloud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = AuthController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    ObjectMapper mapper;

    @BeforeEach
    public void beforeEach() {
        mapper = new ObjectMapper();
    }

    @Test
    void createUser() throws Exception {
        UserCreationDto dto = new UserCreationDto("john", "pass123ff");


        given(userService.createUser(dto)).willReturn(any(UserLoginDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                        .content(mapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createUser_already_exists() throws Exception {
        UserCreationDto dto = new UserCreationDto("AlreadyExistingUser", "password");

        given(userService.createUser(any(UserCreationDto.class))).willThrow(new NotUniqueException(anyString()));


        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

    @Test
    void loginUserExisting() throws Exception {
        UserLoginDto dto = new UserLoginDto("john", "pass123ff");

        given(userService.login(dto)).willReturn(any(AuthResponseDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}