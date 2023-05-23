package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.ideal.IdealConditionDto;
import fungeye.cloud.domain.enities.IdealCondition;
import fungeye.cloud.domain.enities.IdealConditionId;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.persistence.repository.IdealConditionRepository;
import fungeye.cloud.service.mappers.IdealConditionsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IdealConditionServiceTest {

    private IdealConditionRepository repository;
    private IdealConditionService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(IdealConditionRepository.class);
        service = new IdealConditionService(repository);
    }

    @Test
    void testCreateIdealCondition() {
        // Arrange
        IdealConditionDto dtoIn = new IdealConditionDto();
        dtoIn.setMushroomId(1L);
        dtoIn.setDevelopmentStage("spawn run");
        dtoIn.setTempHigh(28.0);
        dtoIn.setTempLow(24.0);
        dtoIn.setHumidityHigh(80.0);
        dtoIn.setHumidityLow(70.0);

        IdealCondition conditionToSave = IdealConditionsMapper.mapToIdealCondition(dtoIn);

        IdealCondition savedCondition = new IdealCondition();
        savedCondition.setId(conditionToSave.getId());
        savedCondition.setMushroom(conditionToSave.getMushroom());
        savedCondition.setTemperatureHigh(conditionToSave.getTemperatureHigh());
        savedCondition.setTemperatureLow(conditionToSave.getTemperatureLow());
        savedCondition.setHumidityHigh(conditionToSave.getHumidityHigh());
        savedCondition.setHumidityLow(conditionToSave.getHumidityLow());
        savedCondition.setCo2High(conditionToSave.getCo2High());
        savedCondition.setCo2Low(conditionToSave.getCo2Low());
        savedCondition.setLightHigh(conditionToSave.getLightHigh());
        savedCondition.setLightLow(conditionToSave.getLightLow());

        Mockito.when(repository.save(conditionToSave)).thenReturn(savedCondition);

        // Act
        IdealConditionDto dtoOut = service.createIdealCondition(dtoIn);

        // Assert
        assertEquals(dtoIn.getMushroomId(), dtoOut.getMushroomId());
        assertEquals(dtoIn.getDevelopmentStage(), dtoOut.getDevelopmentStage());
        assertEquals(dtoIn.getTempHigh(), dtoOut.getTempHigh());
        assertEquals(dtoIn.getTempLow(), dtoOut.getTempLow());
        assertEquals(dtoIn.getHumidityHigh(), dtoOut.getHumidityHigh());
        assertEquals(dtoIn.getHumidityLow(), dtoOut.getHumidityLow());
        assertEquals(dtoIn.getCo2High(), dtoOut.getCo2High());
        assertEquals(dtoIn.getCo2Low(), dtoOut.getCo2Low());
        assertEquals(dtoIn.getLightHigh(), dtoOut.getLightHigh());
        assertEquals(dtoIn.getLightLow(), dtoOut.getLightLow());
    }

    @Test
    void testGetByMushroomId_withValidId_returnsList() {
        // Arrange
        Long mushroomId = 1L;
        Mushroom mushroom = new Mushroom();
        mushroom.setId(mushroomId);
        mushroom.setName("Button Mushroom");

        IdealCondition idealCondition1 = new IdealCondition();
        idealCondition1.setId(new IdealConditionId(mushroomId, "spawn run"));
        idealCondition1.setMushroom(mushroom);
        idealCondition1.setTemperatureLow(20.0);
        idealCondition1.setTemperatureHigh(25.0);
        idealCondition1.setHumidityLow(60.0);
        idealCondition1.setHumidityHigh(80.0);
        idealCondition1.setCo2High(800.0);
        idealCondition1.setCo2Low(200.0);
        idealCondition1.setLightHigh(1000.0);
        idealCondition1.setLightLow(100.0);

        IdealCondition idealCondition2 = new IdealCondition();
        idealCondition2.setId(new IdealConditionId(mushroomId, "pinning"));
        idealCondition2.setMushroom(mushroom);
        idealCondition2.setTemperatureLow(18.0);
        idealCondition2.setTemperatureHigh(22.0);
        idealCondition2.setHumidityLow(75.0);
        idealCondition2.setHumidityHigh(85.0);
        idealCondition2.setCo2High(800.0);
        idealCondition2.setCo2Low(200.0);
        idealCondition2.setLightHigh(1000.0);
        idealCondition2.setLightLow(100.0);

        List<IdealCondition> conditions = new ArrayList<>();
        conditions.add(idealCondition1);
        conditions.add(idealCondition2);

        Mockito.when(repository.findByMushroom_Id(mushroomId)).thenReturn(conditions);

        // Act
        List<IdealConditionDto> result = service.getByMushroomId(mushroomId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        IdealConditionDto dto1 = result.get(0);
        IdealConditionDto dto2 = result.get(1);

        assertEquals(mushroomId, dto1.getMushroomId());
        assertEquals(mushroomId, dto2.getMushroomId());

        assertEquals("spawn run", dto1.getDevelopmentStage());
        assertEquals("pinning", dto2.getDevelopmentStage());

        assertEquals(20, dto1.getTempLow());
        assertEquals(25, dto1.getTempHigh());
        assertEquals(60, dto1.getHumidityLow());
        assertEquals(80, dto1.getHumidityHigh());
        assertEquals(200, dto1.getCo2Low());
        assertEquals(800, dto1.getCo2High());
        assertEquals(100, dto1.getLightLow());
        assertEquals(1000, dto1.getLightHigh());

        assertEquals(18, dto2.getTempLow());
        assertEquals(22, dto2.getTempHigh());
        assertEquals(75, dto2.getHumidityLow());
        assertEquals(85, dto2.getHumidityHigh());
        assertEquals(200, dto2.getCo2Low());
        assertEquals(800, dto2.getCo2High());
        assertEquals(100, dto2.getLightLow());
        assertEquals(1000, dto2.getLightHigh());
    }

    @Test
    void testGetByMushroomIdWithNoResults() {
        Long mushroomId = 1L;
        Mockito.when(repository.findByMushroom_Id(mushroomId)).thenReturn(Collections.emptyList());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getByMushroomId(mushroomId);
        });

        assertEquals("No ideal conditions were found for that mushroom", exception.getMessage());
    }
}