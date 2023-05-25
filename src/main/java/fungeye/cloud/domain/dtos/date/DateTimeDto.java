package fungeye.cloud.domain.dtos.date;

import lombok.Data;

@Data
public class DateTimeDto {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public DateTimeDto(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public DateTimeDto() {
    }

}
