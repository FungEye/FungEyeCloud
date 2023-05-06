package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.MeasuredConditionDto;
import fungeye.cloud.domain.dtos.MeasuredConditionIdDto;
import fungeye.cloud.domain.dtos.SearchConditionsParam;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import fungeye.cloud.service.mappers.MeasuredConditionsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class MeasuredConditionsServiceTest {
    private MeasuredConditionsService service;

    @Mock
    private MeasuredConditionRepository repository;

    @Mock
    private BoxRepository boxRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.service = new MeasuredConditionsService(repository, boxRepository);
    }

    @Test
    void testGetLatestMeasuredCondition() {
        // Given
        long boxId = 1L;
        Box box = new Box();
        box.setId(boxId);
        MeasuredConditionId id = new MeasuredConditionId();
        id.setDateTime(Instant.now());
        id.setBoxId(1L);
        MeasuredCondition measuredCondition = new MeasuredCondition();
        measuredCondition.setBox(box);
        measuredCondition.setTemperature(25.0);
        measuredCondition.setHumidity(60.0);
        measuredCondition.setId(id);

        // When
        when(repository.findTopByBox_IdOrderByIdDesc(anyLong())).thenReturn(measuredCondition);

        // Then
        MeasuredConditionDto result = service.getLatestMeasuredCondition(boxId);
        assertEquals(measuredCondition.getTemperature(), result.getTemperature());
        assertEquals(measuredCondition.getHumidity(), result.getHumidity());
    }

    @Test
    void testGetMeasuredConditions() {
        // Given
        long boxId = 1L;
        int year = 2023;
        int month = 5;
        int day = 6;
        int hour = 12;
        int minute = 0;

        Box box = new Box();
        box.setId(boxId);

        MeasuredConditionId id = new MeasuredConditionId();
        id.setDateTime(Timestamp.valueOf(LocalDateTime.of(year, month, day, hour, minute, 0)).toInstant());
        id.setBoxId(boxId);

        MeasuredCondition measuredCondition = new MeasuredCondition();
        measuredCondition.setBox(box);
        measuredCondition.setTemperature(25.0);
        measuredCondition.setHumidity(60.0);
        measuredCondition.setId(id);

        MeasuredConditionDto dto = MeasuredConditionsMapper.mapToDto(measuredCondition);
        MeasuredConditionIdDto idDto = MeasuredConditionsMapper.mapToIdDto(id);
        dto.setId(idDto);

        List<MeasuredCondition> measuredConditions = new ArrayList<>();
        measuredConditions.add(measuredCondition);

        SearchConditionsParam searchConditionsParam = new SearchConditionsParam();
        searchConditionsParam.setId(boxId);
        searchConditionsParam.setYear(year);
        searchConditionsParam.setMonth(month);
        searchConditionsParam.setDay(day);
        searchConditionsParam.setHour(hour);
        searchConditionsParam.setMinute(minute);

        // When
        when(repository.findAllByBox_Id(boxId)).thenReturn(measuredConditions);
        when(boxRepository.getReferenceById(boxId)).thenReturn(box);
        when(repository.save(any())).thenReturn(measuredCondition);

        // Then
        service.addMeasuredCondition(dto);
        List<MeasuredConditionDto> result = service.getMeasuredConditions(searchConditionsParam);
        assertEquals(1, result.size());
        MeasuredConditionDto resultDto = result.get(0);
        Optional<MeasuredConditionIdDto> resultIdDto = Optional.ofNullable(resultDto.getId());
        assertEquals(idDto.getBoxId(), resultIdDto.map(MeasuredConditionIdDto::getBoxId).orElse(null));
        assertEquals(idDto.getDateTime(), resultIdDto.map(MeasuredConditionIdDto::getDateTime).orElse(null));
        assertEquals(measuredCondition.getTemperature(), resultDto.getTemperature());
        assertEquals(measuredCondition.getHumidity(), resultDto.getHumidity());
    }


}