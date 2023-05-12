package fungeye.cloud.domain.dtos;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

public class SimpleDateDto {
    private int year;
    private int month;
    private int day;

    public SimpleDateDto(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    private void setYear(int year) {
        if (year < 1900 || year > LocalDate.now().getYear()) {
            throw new DateTimeException(String.format("Invalid year. Year should be between %d and %d", 1900, LocalDate.now().getYear()));
        }
        this.year = year;
    }

    private void setMonth(int monthValue) {
        if (monthValue < 1 || monthValue > 12) {
            throw new DateTimeException("Invalid month. Month should be between 1 and 12");
        }
        this.month = monthValue;
    }

    private void setDay(int dayValue) {
        try {
            LocalDate date = LocalDate.of(year, month, dayValue);
            this.day = dayValue;
        }
        catch (DateTimeException e) {
            throw new DateTimeException(String.format("Invalid day. Day should be between 1 and %d", YearMonth.of(year, month).atEndOfMonth().getDayOfMonth()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleDateDto that = (SimpleDateDto) o;
        return year == that.year && month == that.month && day == that.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}
