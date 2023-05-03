package fungeye.cloud.controllers;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fungeye.cloud.domain.dtos.UserCreationDto;
import fungeye.cloud.service.BoxService;
import fungeye.cloud.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @Test
    public void createUser() throws Exception {
        UserCreationDto dto = new UserCreationDto("john", "pass123ff");
        ObjectMapper mapper = new ObjectMapper();

        given(userService.createUser(dto)).willReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .content(mapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void login() throws Exception {
        given(userService.login("john", "pass123ff")).willReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .queryParam("username", "john")
                        .queryParam("password", "pass123ff")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
}