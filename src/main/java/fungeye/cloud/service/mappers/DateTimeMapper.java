package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.date.DateTimeDto;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateTimeMapper {

    private DateTimeMapper() {
        throw new IllegalStateException("Utility class");
    }

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

    public static LocalDate mapFromDateDto(DateTimeDto dto) {
        return LocalDate.of(dto.getYear(), dto.getMonth(), dto.getDay());
    }

    public static Instant mapToInstant(DateTimeDto dto) {
        LocalDateTime temp = LocalDateTime.of(dto.getYear(), dto.getMonth(), dto.getDay(), dto.getHour(), dto.getMinute(), dto.getSecond());
        return temp.toInstant(ZoneOffset.ofHours(0));
    }
}
