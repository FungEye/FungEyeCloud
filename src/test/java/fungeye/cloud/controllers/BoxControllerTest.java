package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.BoxDto;
import fungeye.cloud.service.BoxService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = BoxController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class BoxControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoxService boxService;

    @InjectMocks
    private BoxController boxController;

    @Test
    void testCreateBox() throws Exception {
        BoxDto boxDto = new BoxDto();
        boxDto.setId(1L);

        given(boxService.createBox()).willReturn(boxDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/box")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetBoxById() throws Exception {
        BoxDetailsDto boxDetailsDto = new BoxDetailsDto();
        boxDetailsDto.setId(1L);

        given(boxService.getById(1L)).willReturn(boxDetailsDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/box1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetAllBoxes() throws Exception {
        BoxDetailsDto boxDetailsDto = new BoxDetailsDto();
        boxDetailsDto.setId(1L);

        List<BoxDetailsDto> dtos = new ArrayList<>();
        dtos.add(boxDetailsDto);

        given(boxService.getAll()).willReturn(dtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/boxes"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllBoxesByUserId() {
        String userName = "Liepa";
        BoxDto dto1 = new BoxDto();
        BoxDto dto2 = new BoxDto();

        List<BoxDto> dtos = new ArrayList<>();
        dtos.add(dto1);
        dtos.add(dto2);
        when(boxService.getAllEmptyByUserName(userName)).thenReturn(dtos);

        ResponseEntity<List<BoxDto>> responseEntity = boxController.getAllBoxesByUserName(userName);

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(dtos, responseEntity.getBody());
        verify(boxService, times(1)).getAllEmptyByUserName(userName);

    }


}
