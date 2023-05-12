package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDateDtoTest {

    @Test
    void TestConstructorLegalDate() {
        assertDoesNotThrow(() -> new SimpleDateDto(2023, 5, 1));
    }

    @Test
    void TestConstructorIllegalYear() {
        assertThrows(DateTimeException.class, () -> new SimpleDateDto(0, 5, 1));
    }

    @Test
    void TestConstructorIllegalMonth() {
        assertThrows(DateTimeException.class, () -> new SimpleDateDto(2023, 13, 1));
    }

    @Test
    void TestConstructorIllegalDay() {
        assertThrows(DateTimeException.class, () -> new SimpleDateDto(2023, 4, 31));
    }


    @Test
    void getYear() {
        SimpleDateDto dto = new SimpleDateDto(2023,5,12);
        assertEquals(2023, dto.getYear());
    }

    @Test
    void getMonth() {
        SimpleDateDto dto = new SimpleDateDto(2023,5,12);
        assertEquals(5, dto.getMonth());
    }

    @Test
    void getDay() {
        SimpleDateDto dto = new SimpleDateDto(2023,5,12);
        assertEquals(12, dto.getDay());
    }

    @Test
    void testEquals() {
        SimpleDateDto dto = new SimpleDateDto(2023,5,12);
        SimpleDateDto dto2 = new SimpleDateDto(2023,5,12);
        assertEquals(dto, dto2);
    }

    @Test
    void testNotEquals() {
        SimpleDateDto dto = new SimpleDateDto(2023,5,12);
        SimpleDateDto dto2 = new SimpleDateDto(2022,6,11);
        assertNotEquals(dto, dto2);
    }

    @Test
    void testHashCodeEqualObjects() {
        SimpleDateDto dto = new SimpleDateDto(2023,5,12);
        SimpleDateDto dto2 = new SimpleDateDto(2023,5,12);
        assertEquals(dto.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeNonEqualObjects() {
        SimpleDateDto dto = new SimpleDateDto(2023,5,12);
        SimpleDateDto dto2 = new SimpleDateDto(2022,6,11);
        assertNotEquals(dto.hashCode(), dto2.hashCode());
    }
}