package fungeye.cloud.controllers;

import fungeye.cloud.domain.dtos.IdealConditionDto;
import fungeye.cloud.service.IdealConditionService;
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
class IdealConditionsControllerTest {

    @Mock
    private IdealConditionService service;

    @InjectMocks
    private IdealConditionsController controller;

    @Test
    void createIdealCondition_shouldCreateNewIdealConditionAndReturn201() {
        // given
        IdealConditionDto inputDto = new IdealConditionDto();
        IdealConditionDto outputDto = new IdealConditionDto();
        when(service.createIdealCondition(inputDto)).thenReturn(outputDto);

        // when
        ResponseEntity<IdealConditionDto> responseEntity = controller.createIdealCondition(inputDto);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(outputDto, responseEntity.getBody());
        verify(service, times(1)).createIdealCondition(inputDto);
    }

    @Test
    void getIdealConditionsByMushroomId_shouldReturnListOfIdealConditionsAndReturn302() {
        // given
        Long mushroomId = 123L;
        IdealConditionDto idealConditionDto1 = new IdealConditionDto();
        IdealConditionDto idealConditionDto2 = new IdealConditionDto();
        List<IdealConditionDto> expectedOutput = new ArrayList<>();
        expectedOutput.add(idealConditionDto1);
        expectedOutput.add(idealConditionDto2);
        when(service.getByMushroomId(mushroomId)).thenReturn(expectedOutput);

        // when
        ResponseEntity<List<IdealConditionDto>> responseEntity = controller.getIdealConditionsByMushroomId(mushroomId);

        // then
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(expectedOutput, responseEntity.getBody());
        verify(service, times(1)).getByMushroomId(mushroomId);
    }
}
