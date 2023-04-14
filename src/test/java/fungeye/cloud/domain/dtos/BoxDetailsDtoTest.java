package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.dtos.BoxDetailsDto;
import fungeye.cloud.domain.dtos.GrowDto;
import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BoxDetailsDtoTest {

    @Mock
    private MeasuredConditionDto measuredConditionDtoMock;

    @Mock
    private GrowDto growDtoMock;

    private BoxDetailsDto boxDetailsDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<MeasuredConditionDto> measuredConditions = new ArrayList<>();
        measuredConditions.add(measuredConditionDtoMock);
        List<GrowDto> grows = new ArrayList<>();
        grows.add(growDtoMock);
        boxDetailsDto = new BoxDetailsDto(1L, measuredConditions, grows);
    }

    @Test
    public void testConstructor() {
        assertEquals(1L, boxDetailsDto.getId());
        assertEquals(1, boxDetailsDto.getConditions().size());
        assertEquals(measuredConditionDtoMock, boxDetailsDto.getConditions().get(0));
        assertEquals(1, boxDetailsDto.getGrows().size());
        assertEquals(growDtoMock, boxDetailsDto.getGrows().get(0));
    }

    @Test
    public void testGettersAndSetters() {
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
