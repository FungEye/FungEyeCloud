package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.service.BoxService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BoxController.class)
public class BoxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoxService boxService;

    @InjectMocks
    private BoxController boxController;

    @Test
    public void testCreateBox() throws Exception {
        BoxDto boxDto = new BoxDto();
        boxDto.setId(1L);

        given(boxService.createBox()).willReturn(boxDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/box")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetBoxById() throws Exception {
        BoxDetailsDto boxDetailsDto = new BoxDetailsDto();
        boxDetailsDto.setId(1L);

        given(boxService.getById(1L)).willReturn(boxDetailsDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/box1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
