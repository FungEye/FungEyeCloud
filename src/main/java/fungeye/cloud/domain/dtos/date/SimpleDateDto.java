package fungeye.cloud.domain.dtos.date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

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
