package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.HarvestCreationDto;
import fungeye.cloud.domain.dtos.HarvestDetailsDto;
import fungeye.cloud.service.HarvestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HarvestControllerTest {

    @Mock
    private HarvestService service;

    @InjectMocks
    private HarvestController controller;

    @Test
    void createHarvestSuccess() {
        HarvestCreationDto input = new HarvestCreationDto();
        HarvestDetailsDto result = new HarvestDetailsDto();
        when(service.addHarvest(input)).thenReturn(result);

        ResponseEntity<HarvestDetailsDto> responseEntity = controller.createHarvest(input);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(result, responseEntity.getBody());
        verify(service, times(1)).addHarvest(input);
    }

    @Test
    void getAllHarvestsByUserIdWhenOneEntry() {
        String username = "john";
        HarvestDetailsDto result1 = new HarvestDetailsDto();
        List<HarvestDetailsDto> expectedOutput = new ArrayList<>();
        expectedOutput.add(result1);
        when(service.getAllHarvestsByUsername(username)).thenReturn(expectedOutput);

        ResponseEntity<List<HarvestDetailsDto>> responseEntity = controller.getAllHarvestsByUsername(username);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedOutput, responseEntity.getBody());
        verify(service, times(1)).getAllHarvestsByUsername(username);
    }

    @Test
    void getAllHarvestsByUserIdWhenFiveEntries() {
        String username = "john";
        HarvestDetailsDto result1 = new HarvestDetailsDto();
        HarvestDetailsDto result2 = new HarvestDetailsDto();
        HarvestDetailsDto result3 = new HarvestDetailsDto();
        HarvestDetailsDto result4 = new HarvestDetailsDto();
        HarvestDetailsDto result5 = new HarvestDetailsDto();
        List<HarvestDetailsDto> expectedOutput = new ArrayList<>();
        expectedOutput.add(result1);
        expectedOutput.add(result2);
        expectedOutput.add(result3);
        expectedOutput.add(result4);
        expectedOutput.add(result5);
        when(service.getAllHarvestsByUsername(username)).thenReturn(expectedOutput);

        ResponseEntity<List<HarvestDetailsDto>> responseEntity = controller.getAllHarvestsByUsername(username);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedOutput, responseEntity.getBody());
        verify(service, times(1)).getAllHarvestsByUsername(username);
    }

    @Test
    void getAllHarvestsByGrowId() {
        String username = "john";
        HarvestDetailsDto result1 = new HarvestDetailsDto();
        HarvestDetailsDto result2 = new HarvestDetailsDto();
        HarvestDetailsDto result3 = new HarvestDetailsDto();
        HarvestDetailsDto result4 = new HarvestDetailsDto();
        HarvestDetailsDto result5 = new HarvestDetailsDto();
        List<HarvestDetailsDto> expectedOutput = new ArrayList<>();
        expectedOutput.add(result1);
        expectedOutput.add(result2);
        expectedOutput.add(result3);
        expectedOutput.add(result4);
        expectedOutput.add(result5);
        when(service.getAllHarvestsByGrowId(1L)).thenReturn(expectedOutput);

        ResponseEntity<List<HarvestDetailsDto>> responseEntity = controller.getAllHarvestsByGrowId(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedOutput, responseEntity.getBody());
        verify(service, times(1)).getAllHarvestsByGrowId(1L);
    }
}