package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.dtos.box.BoxDetailsDto;
import fungeye.cloud.domain.dtos.grow.GrowDto;
import fungeye.cloud.domain.dtos.measured.MeasuredConditionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoxDetailsDtoTest {

    @Mock
    private MeasuredConditionDto measuredConditionDtoMock;

    @Mock
    private GrowDto growDtoMock;

    private BoxDetailsDto boxDetailsDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<MeasuredConditionDto> measuredConditions = new ArrayList<>();
        measuredConditions.add(measuredConditionDtoMock);
        List<GrowDto> grows = new ArrayList<>();
        grows.add(growDtoMock);
        boxDetailsDto = new BoxDetailsDto();
        boxDetailsDto.setId(1L);
        boxDetailsDto.setConditions(measuredConditions);
        boxDetailsDto.setGrows(grows);
    }

    @Test
    void testConstructor() {
        assertEquals(1L, boxDetailsDto.getId());
        assertEquals(1, boxDetailsDto.getConditions().size());
        assertEquals(measuredConditionDtoMock, boxDetailsDto.getConditions().get(0));
        assertEquals(1, boxDetailsDto.getGrows().size());
        assertEquals(growDtoMock, boxDetailsDto.getGrows().get(0));
    }

    @Test
    void testGettersAndSetters() {
        List<MeasuredConditionDto> newMeasuredConditions = new ArrayList<>();
        newMeasuredConditions.add(measuredConditionDtoMock);
        List<GrowDto> newGrows = new ArrayList<>();
        newGrows.add(growDtoMock);

        boxDetailsDto.setId(2L);
        assertEquals(2L, boxDetailsDto.getId());

        boxDetailsDto.setConditions(newMeasuredConditions);
        assertEquals(newMeasuredConditions, boxDetailsDto.getConditions());

        boxDetailsDto.setGrows(newGrows);
        assertEquals(newGrows, boxDetailsDto.getGrows());
    }
}
