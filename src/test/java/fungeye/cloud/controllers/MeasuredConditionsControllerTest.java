package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.HistoricalMeasurementDto;
import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.MeasuredConditionIdDto;
import fungeye.cloud.domain.dtos.SingleMeasurementDto;
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
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;


@WebMvcTest(value = MeasuredConditionsController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class MeasuredConditionsControllerTest {


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

    @Test
    void getHistoricalDataWithContent() throws Exception {
        // Arrange
        HistoricalMeasurementDto expected = new HistoricalMeasurementDto();
        List<SingleMeasurementDto> temperature = new ArrayList<>();
        List<SingleMeasurementDto> humidity = new ArrayList<>();
        List<SingleMeasurementDto> co2 = new ArrayList<>();
        List<SingleMeasurementDto> light = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SingleMeasurementDto toAdd = new SingleMeasurementDto();
            toAdd.setDateTime(LocalDateTime.now().plusMinutes(i));
            toAdd.setValue(10 + (i * 1.5));
            temperature.add(toAdd);
            humidity.add(toAdd);
            co2.add(toAdd);
            light.add(toAdd);
        }

        expected.setTemperature(temperature);
        expected.setHumidity(humidity);
        expected.setCo2(co2);
        expected.setLight(light);

        Mockito.when(service.getHistoricalMeasurements(anyLong())).thenReturn(expected);


        // Act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/box1/measurements/historical")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature[0].value").value(10.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity[0].value").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.co2[0].value").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.light[0].value").value(10));
    }

    @Test
    void getHistoricalMeasurementsWithNoData() throws Exception {
        // Arrange
        HistoricalMeasurementDto expected = new HistoricalMeasurementDto();
        List<SingleMeasurementDto> temperature = new ArrayList<>();
        List<SingleMeasurementDto> humidity = new ArrayList<>();
        List<SingleMeasurementDto> co2 = new ArrayList<>();
        List<SingleMeasurementDto> light = new ArrayList<>();

        expected.setTemperature(temperature);
        expected.setHumidity(humidity);
        expected.setCo2(co2);
        expected.setLight(light);

        Mockito.when(service.getHistoricalMeasurements(anyLong())).thenReturn(expected);


        // Act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/box1/measurements/historical")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature[0]").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity[0]").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.co2[0]").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.light[0]").doesNotExist());
    }
}
