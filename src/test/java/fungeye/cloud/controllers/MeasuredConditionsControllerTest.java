package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.MeasuredConditionIdDto;
import fungeye.cloud.service.MeasuredConditionsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


@WebMvcTest(value = MeasuredConditionsController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
public class MeasuredConditionsControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeasuredConditionsService service;

    @Test
    void getMeasuredConditions_ReturnsListOfMeasuredConditions() throws Exception {
        // Arrange
        MeasuredConditionIdDto idDto = new MeasuredConditionIdDto();
        idDto.setBoxId(1L);

        MeasuredConditionDto measuredConditionDto = new MeasuredConditionDto();
        measuredConditionDto.setId(idDto);
        measuredConditionDto.setTemperature(25.0);
        measuredConditionDto.setHumidity(40.0);

        Mockito.when(service.getMeasuredConditions(any())).thenReturn(Collections.singletonList(measuredConditionDto));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/box1/measurements")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id.boxId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].temperature").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].humidity").value(40.0));
    }

    @Test
    void getLatestMeasurements_ReturnsLatestMeasuredCondition() throws Exception {
        // Arrange
        MeasuredConditionIdDto idDto = new MeasuredConditionIdDto();
        idDto.setBoxId(1L);

        MeasuredConditionDto measuredConditionDto = new MeasuredConditionDto();
        measuredConditionDto.setId(idDto);
        measuredConditionDto.setTemperature(25.0);
        measuredConditionDto.setHumidity(40.0);

        Mockito.when(service.getLatestMeasuredCondition(anyLong())).thenReturn(measuredConditionDto);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/box1/measurements/latest")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id.boxId").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(25.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity").value(40.0));
    }
}
