package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.measured.*;
import fungeye.cloud.domain.enities.Box;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.MeasuredCondition;
import fungeye.cloud.persistence.repository.BoxRepository;
import fungeye.cloud.persistence.repository.GrowRepository;
import fungeye.cloud.persistence.repository.MeasuredConditionRepository;
import fungeye.cloud.security.JwtGenerator;
import fungeye.cloud.service.mappers.MeasuredConditionsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fungeye.cloud.service.mappers.MeasuredConditionsMapper.*;

@Service
@Slf4j
public class MeasuredConditionsService {

    private final MeasuredConditionRepository repository;
    private final BoxRepository boxRepository;
    private final GrowRepository growRepository;
    private JwtGenerator generator;


    public MeasuredConditionsService(MeasuredConditionRepository repository, BoxRepository boxRepository,
                                     JwtGenerator generator, GrowRepository growRepository) {
        this.repository = repository;
        this.boxRepository = boxRepository;
        this.generator = generator;
        this.growRepository = growRepository;
    }

    public MeasuredConditionDto getLatestMeasuredCondition(long boxId, String token) {
        Box bEntity = boxRepository.getReferenceById(boxId);
        String username = bEntity.getUserEntity().getUsername();
        String tokenUsername = generator.getUsernameFromJwt(token.substring(7));
        if (username.equals(tokenUsername)) return mapToDto(repository.findTopByBox_IdOrderByIdDesc(boxId));
        else
            throw new BadCredentialsException(String.format("User: %s is not authorized to access boxes belonging to user: %s.", tokenUsername, username));
    }

    public MeasuredConditionWithStageDto getLatestMeasuredConditionWithStage(long boxId, String token) {
        Box bEntity = boxRepository.getReferenceById(boxId);
        String username = bEntity.getUserEntity().getUsername();
        String tokenUsername = generator.getUsernameFromJwt(token.substring(7));
        if (username.equals(tokenUsername)) {
            MeasuredConditionWithStageDto withStageDto = mapToMeasuredConditionWithStageDto(repository.findTopByBox_IdOrderByIdDesc(boxId));
            Grow currentGrow = growRepository.findByBox_IdAndIsActive(boxId, true);
            withStageDto.setDevelopmentStage(currentGrow.getDevelopmentStage());
            withStageDto.setGrowId(currentGrow.getId());
            return withStageDto;
        }
        else
            throw new BadCredentialsException(String.format("User: %s is not authorized to access boxes belonging to user: %s.", tokenUsername, username));
    }

    public List<MeasuredConditionDto> getMeasuredConditions(SearchConditionsParam param) {
        List<MeasuredCondition> conditions = repository.findAllByBox_Id(param.getId());
        List<MeasuredCondition> result = new ArrayList<>();

        for (MeasuredCondition condition : conditions) {
            LocalDateTime dateTime = LocalDateTime.ofInstant(condition.getId().getDateTime(), ZoneId.systemDefault());

            if (!(
                    (param.getYear() != null && dateTime.getYear() != param.getYear()) ||
                            (param.getMonth() != null && dateTime.getMonthValue() != param.getMonth()) ||
                            (param.getDay() != null && dateTime.getDayOfMonth() != param.getDay()) ||
                            (param.getHour() != null && dateTime.getHour() != param.getHour()) ||
                            (param.getMinute() != null && dateTime.getMinute() != param.getMinute()))) {
                result.add(condition);
            }
        }
        return mapToDtoList(result);
    }

    public void addMeasuredCondition(MeasuredConditionDto dto) {
        MeasuredCondition toCreate = mapToEntity(dto);
        toCreate.setBox(boxRepository.getReferenceById(dto.getId().getBoxId()));
        MeasuredConditionDto response = mapToDto(repository.save(toCreate));
        log.info(String.format("Measurement persisted in database for box # %d", response.getId().getBoxId()));
    }

    // Because of the current limitation to one device, this method duplicates the measurement
    // and adds it to all boxes that have an active grow
    public void addMeasuredConditionCopyToAllActiveGrows(MeasuredConditionDto dto) {
        List<Box> allBoxes = boxRepository.findAll();
        // Find all boxes that have active grows
        for (Box box:
             allBoxes) {
            Grow activeGrow = growRepository.findByBox_IdAndIsActive(box.getId(), true);
            if (activeGrow != null) {
                // Set the box id of the measurement and persist
                MeasuredConditionIdDto idDto = dto.getId();
                idDto.setBoxId(box.getId());
                dto.setId(idDto);
                MeasuredCondition toCreate = mapToEntity(dto);
                toCreate.setBox(boxRepository.getReferenceById(dto.getId().getBoxId()));
                MeasuredConditionDto response = mapToDto(repository.save(toCreate));
                log.info(String.format("Measurement persisted in database for box # %d", response.getId().getBoxId()));
            }
        }

    }

    public HistoricalMeasurementDto getHistoricalMeasurements(Long boxId, String token) {
        Box bEntity = boxRepository.getReferenceById(boxId);
        String username = bEntity.getUserEntity().getUsername();
        String tokenUsername = generator.getUsernameFromJwt(token.substring(7));
        if (username.equals(tokenUsername)) {

            List<MeasuredCondition> measuredConditions = repository.findAllByBox_Id(boxId);
            HistoricalMeasurementDto result = new HistoricalMeasurementDto();
            result.setTemperature(new ArrayList<>());
            result.setLight(new ArrayList<>());
            result.setHumidity(new ArrayList<>());
            result.setCo2(new ArrayList<>());
            for (MeasuredCondition cond : measuredConditions
            ) {
                // Temperature
                SingleMeasurementDto temp = new SingleMeasurementDto();
                temp.setValue(cond.getTemperature());
                temp.setDateTime(LocalDateTime.ofInstant(cond.getId().getDateTime(), ZoneId.systemDefault()));
                result.getTemperature().add(temp);

                // Humidity
                SingleMeasurementDto humidity = new SingleMeasurementDto();
                humidity.setValue(cond.getHumidity());
                humidity.setDateTime(LocalDateTime.ofInstant(cond.getId().getDateTime(), ZoneId.systemDefault()));
                result.getHumidity().add(humidity);

                // CO2
                SingleMeasurementDto co2 = new SingleMeasurementDto();
                co2.setValue(cond.getCo2());
                co2.setDateTime(LocalDateTime.ofInstant(cond.getId().getDateTime(), ZoneId.systemDefault()));
                result.getCo2().add(co2);

                // Light
                SingleMeasurementDto light = new SingleMeasurementDto();
                light.setValue(cond.getLight());
                light.setDateTime(LocalDateTime.ofInstant(cond.getId().getDateTime(), ZoneId.systemDefault()));
                result.getLight().add(light);
            }

            return result;
        }
        else throw new BadCredentialsException(String.format("User: %s is not authorized to access boxes belonging to user: %s.", tokenUsername, username));

    }

    public List<MeasuredConditionDto> getLatestForUser(String username, String token) {
        String jwtUsername = generator.getUsernameFromJwt(token.substring(7));
        if (jwtUsername.equals(username)) {
            List<MeasuredConditionDto> conditionDtos = new ArrayList<>();
            List<Box> boxes = boxRepository.findBoxesByUserEntity_Username(username);
            for (Box box :
                    boxes) {
                MeasuredCondition foundCondition = repository.findFirstById_BoxIdOrderById_DateTimeDesc(box.getId());
                if (foundCondition != null) {
                    conditionDtos.add(mapToDto(repository.findFirstById_BoxIdOrderById_DateTimeDesc(box.getId())));
                }
            }
            return conditionDtos;
        } else
            throw new BadCredentialsException(String.format("User: %s is not authorized to access boxes belonging to user: %s.", jwtUsername, username));
    }

    public List<MeasuredConditionDto> getLatestForUserWithStage(String username, String token) {
        String jwtUsername = generator.getUsernameFromJwt(token.substring(7));
        if (jwtUsername.equals(username)) {
            List<MeasuredConditionDto> conditionWithStageDtos = new ArrayList<>();
            List<Box> boxes = boxRepository.findBoxesByUserEntity_Username(username);
            for (Box box :
                    boxes) {
                MeasuredCondition foundCondition = repository.findFirstById_BoxIdOrderById_DateTimeDesc(box.getId());
                if (foundCondition != null) {
                    Grow foundGrow = growRepository.findFirstByBox_IdOrderByDateStartedDesc(box.getId());
                    if (foundGrow != null) {
                        MeasuredConditionWithStageDto withStageDto = MeasuredConditionsMapper.mapToMeasuredConditionWithStageDto(foundCondition);
                        withStageDto.setDevelopmentStage(foundGrow.getDevelopmentStage());
                        withStageDto.setGrowId(foundGrow.getId());
                        conditionWithStageDtos.add(withStageDto);
                    }
                    else {
                        MeasuredConditionWithStageDto noStageDto = MeasuredConditionsMapper.mapToMeasuredConditionWithStageDto(foundCondition);
                        noStageDto.setDevelopmentStage("");
                        conditionWithStageDtos.add(noStageDto);
                    }
                }
            }
            return conditionWithStageDtos;
        } else
            throw new BadCredentialsException(String.format("User: %s is not authorized to access boxes belonging to user: %s.", jwtUsername, username));
    }
}
