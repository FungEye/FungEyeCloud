package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HarvestCreationDtoTest {

    private HarvestCreationDto dto;
    private HarvestCreationDto dto2;
    private SimpleDateDto date;
    private SimpleDateDto date2;

    @BeforeEach
    void setup() {
        date = new SimpleDateDto(2023,5,12);
        date2 = new SimpleDateDto(2023,5,13);
        dto = new HarvestCreationDto();
        dto2 = new HarvestCreationDto();
    }

    @Test
    void setAndGetGrowId() {
        dto.setGrowId(1L);
        assertEquals(1L, dto.getGrowId());
    }

    @Test
    void setAndGetWeight() {
        dto.setWeight(22.5);
        assertEquals(22.5, dto.getWeight());
    }

    @Test
    void setAndGetHarvestDate() {
        dto.setHarvestDate(date);
        assertEquals(date, dto.getHarvestDate());
    }

    @Test
    void testEquals() {
        dto.setGrowId(1L);
        dto.setWeight(22.5);
        dto.setHarvestDate(date);

        dto2.setGrowId(1L);
        dto2.setWeight(22.5);
        dto2.setHarvestDate(date);

        assertEquals(dto, dto2);
    }

    @Test
    void testNotEquals() {
        dto.setGrowId(1L);
        dto.setWeight(22.5);
        dto.setHarvestDate(date);

        dto2.setGrowId(2L);
        dto2.setWeight(12.5);
        dto2.setHarvestDate(date2);

        assertNotEquals(dto, dto2);
    }

    @Test
    void testHashCode() {
        dto.setGrowId(1L);
        dto.setWeight(22.5);
        dto.setHarvestDate(date);

        dto2.setGrowId(1L);
        dto2.setWeight(22.5);
        dto2.setHarvestDate(date);

        assertEquals(dto.hashCode(), dto2.hashCode());
    }

    @Test
    void testUnEqualHashCode() {
        dto.setGrowId(1L);
        dto.setWeight(22.5);
        dto.setHarvestDate(date);

        dto2.setGrowId(2L);
        dto2.setWeight(12.5);
        dto2.setHarvestDate(date2);

        assertNotEquals(dto.hashCode(), dto2.hashCode());
    }
}