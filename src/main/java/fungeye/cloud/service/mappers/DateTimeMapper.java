package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.DateTimeDto;

import java.time.*;

public class DateTimeMapper {

    public static DateTimeDto mapToDateDto(Instant instant)
    {
        DateTimeDto dto = new DateTimeDto();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(0));
        dto.setDay(dateTime.getDayOfMonth());
        dto.setMonth(dateTime.getMonthValue());
        dto.setYear(dateTime.getYear());

        dto.setHour(dateTime.getHour());
        dto.setMinute(dateTime.getMinute());
        dto.setSecond(dateTime.getSecond());

        return dto;
    }

    public static DateTimeDto mapToDateDto(LocalDate localDate) {
        DateTimeDto dto = new DateTimeDto();
        dto.setDay(localDate.getDayOfMonth());
        dto.setMonth(localDate.getMonthValue());
        dto.setYear(localDate.getYear());

        return dto;
    }

    public static Instant mapToInstant(DateTimeDto dto) {
        LocalDateTime temp = LocalDateTime.of(dto.getYear(), dto.getMonth(), dto.getDay(), dto.getHour(), dto.getMinute(), dto.getSecond());
        return temp.toInstant(ZoneOffset.ofHours(0));
    }
}
