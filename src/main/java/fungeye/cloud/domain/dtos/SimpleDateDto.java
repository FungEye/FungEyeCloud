package fungeye.cloud.domain.dtos;

import lombok.*;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

@Data
@Setter(AccessLevel.PRIVATE)
public class SimpleDateDto implements Serializable {
    private int year;
    private int month;
    private int day;

    public SimpleDateDto(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }
}
