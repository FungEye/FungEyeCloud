package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.measured.*;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.domain.enities.MeasuredConditionId;
import fungeye.cloud.domain.enities.users.UserEntity;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.persistence.repository.GrowRepository;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import fungeye.cloud.security.JwtGenerator;
import fungeye.cloud.service.mappers.MeasuredConditionsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class MeasuredConditionsServiceTest {
    private MeasuredConditionsService service;

    @Mock
    private MeasuredConditionRepository repository;

    @Mock
    private BoxRepository boxRepository;

    @Mock
    private JwtGenerator generator;

    @Mock
    private GrowRepository growRepository;

    String token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.service = new MeasuredConditionsService(repository, boxRepository, generator, growRepository);
        token = "TOKEN_VALUE";
    }

    @Test
    void testGetLatestMeasuredCondition() {
        // Given
        long boxId = 1L;

        UserEntity user = new UserEntity();
        user.setUsername("john");

        Box box = new Box();
        box.setId(boxId);
        box.setUserEntity(user);

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
        when(generator.getUsernameFromJwt(token.substring(7))).thenReturn("john");
        when(boxRepository.getReferenceById(boxId)).thenReturn(box);

        // Then
        MeasuredConditionDto result = service.getLatestMeasuredCondition(boxId, token);
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

    @Test
    void testAddMeasuredConditionCopyToAllActiveGrows() {
        // Todo: Write test
        // Create measurements, add them, test the size and boxids
    }

    @Test
    void getHistoricalMeasurementsWithContent() {
        //Given
        long boxId = 1L;

        UserEntity user = new UserEntity();
        user.setUsername("john");

        Box box = new Box();
        box.setId(boxId);
        box.setUserEntity(user);


        List<MeasuredCondition> list = new ArrayList<>();
        Instant timestamp = Instant.now();
        for (int i = 0; i < 10; i++) {
            MeasuredCondition cond = new MeasuredCondition();
            MeasuredConditionId id = new MeasuredConditionId();
            id.setDateTime(timestamp.plusSeconds(i * 60));
            id.setBoxId(1L);
            cond.setBox(box);
            cond.setTemperature(25.0 * i);
            cond.setHumidity(60.0 * i);
            cond.setLight((double) (100 * i));
            cond.setCo2((double) (23 * i));
            cond.setId(id);

            list.add(cond);
        }

        HistoricalMeasurementDto actual = new HistoricalMeasurementDto();
        List<SingleMeasurementDto> temp = new ArrayList<>();
        List<SingleMeasurementDto> humid = new ArrayList<>();
        List<SingleMeasurementDto> co2 = new ArrayList<>();
        List<SingleMeasurementDto> light = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            LocalDateTime dateTime = LocalDateTime.ofInstant(timestamp.plusSeconds(i * 60), ZoneId.systemDefault());
            SingleMeasurementDto temperature = new SingleMeasurementDto();
            temperature.setValue((double) (25 * i));
            temperature.setDateTime(dateTime);
            temp.add(temperature);

            SingleMeasurementDto humidity = new SingleMeasurementDto();
            humidity.setValue((double) (60 * i));
            humidity.setDateTime(dateTime);
            humid.add(humidity);

            SingleMeasurementDto co = new SingleMeasurementDto();
            co.setValue((double) (23 * i));
            co.setDateTime(dateTime);
            co2.add(co);

            SingleMeasurementDto li = new SingleMeasurementDto();
            li.setValue((double) (100 * i));
            li.setDateTime(dateTime);
            light.add(li);
        }
        actual.setLight(light);
        actual.setTemperature(temp);
        actual.setHumidity(humid);
        actual.setCo2(co2);

        //When
        when(repository.findAllByBox_Id(boxId)).thenReturn(list);
        when(generator.getUsernameFromJwt(token.substring(7))).thenReturn("john");
        when(boxRepository.getReferenceById(boxId)).thenReturn(box);


        //Then
        assertEquals(actual, service.getHistoricalMeasurements(boxId, token));
    }

    @Test
    void testGetLatestForUser() {
        //Given
        UserEntity user = new UserEntity();
        user.setUsername("john");

        Box box = new Box();
        box.setId(1L);
        box.setUserEntity(user);

        Set<MeasuredCondition> set = new HashSet<>();
        Instant timestamp = Instant.now();
        for (int i = 0; i < 10; i++) {
            MeasuredCondition cond = new MeasuredCondition();
            MeasuredConditionId id = new MeasuredConditionId();
            id.setDateTime(timestamp.plusSeconds(i * 60));
            id.setBoxId(1L);
            cond.setBox(box);
            cond.setTemperature(25.0 * i);
            cond.setHumidity(60.0 * i);
            cond.setLight((double) (100 * i));
            cond.setCo2((double) (23 * i));
            cond.setId(id);

            set.add(cond);
        }

        box.setMeasuredConditions(set);
        List<MeasuredCondition> conditionsList = set.stream().toList();

        List<Box> johnsBoxes = new ArrayList<>();
        johnsBoxes.add(box);

        List<MeasuredConditionDto> expected = MeasuredConditionsMapper.mapToDtoList(set);

        // When
        when(generator.getUsernameFromJwt(token.substring(7))).thenReturn("john");
        when(boxRepository.findBoxesByUserEntity_Username("john")).thenReturn(johnsBoxes);
        when(repository.findFirstById_BoxIdOrderById_DateTimeDesc(1L)).thenReturn(conditionsList.get(9));

        MeasuredConditionDto actual = service.getLatestForUser("john", token).get(0);

        assertEquals(MeasuredConditionsMapper.mapToDto(conditionsList.get(9)), actual);
    }
}