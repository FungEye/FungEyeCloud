package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class HarvestDetailsDtoTest {

    private HarvestDetailsDto dto;

    @Test
    void getId() {
        dto = new HarvestDetailsDto();
        dto.setId(1L);
        assertEquals(1L, dto.getId());
    }

    @Test
    void setAndGetMushroomName() {
        dto = new HarvestDetailsDto();
        dto.setId(1L);
        dto.setMushroomName("Mushroom");
        assertEquals("Mushroom", dto.getMushroomName());
    }

    @Test
    void setAndGetGrowId() {
        dto = new HarvestDetailsDto();
        dto.setId(1L);
        dto.setGrowId(1L);
        assertEquals(1L, dto.getGrowId());
    }

    @Test
    void setAndGetWeight() {
        dto = new HarvestDetailsDto();
        dto.setId(1L);
        dto.setWeight(22.5);
        assertEquals(22.5, dto.getWeight());
    }

    @Test
    void setAndGetHarvestDate() {
        dto = new HarvestDetailsDto();
        dto.setId(1L);
        SimpleDateDto date = new SimpleDateDto(2023,5,12);
        dto.setHarvestDate(date);
        assertEquals(date, dto.getHarvestDate());
    }

    @Test
    void testEquals() {
        dto = new HarvestDetailsDto();
        dto.setId(1L);
        dto.setHarvestDate(new SimpleDateDto(2023,5,12));
        dto.setGrowId(1L);
        dto.setWeight(22.5);
        dto.setMushroomName("Mushroom");

        HarvestDetailsDto dto2 = new HarvestDetailsDto();
        dto2.setId(1L);
        dto2.setHarvestDate(new SimpleDateDto(2023,5,12));
        dto2.setGrowId(1L);
        dto2.setWeight(22.5);
        dto2.setMushroomName("Mushroom");

        assertEquals(dto2, dto);
    }

    @Test
    void testNotEqual() {
        dto = new HarvestDetailsDto();
        dto.setId(1L);
        dto.setHarvestDate(new SimpleDateDto(2023,5,12));
        dto.setGrowId(1L);
        dto.setWeight(22.5);
        dto.setMushroomName("Mushroom");

        HarvestDetailsDto dto2 = new HarvestDetailsDto();
        dto2.setId(2L);
        dto2.setHarvestDate(new SimpleDateDto(2013,6,20));
        dto2.setGrowId(4L);
        dto2.setWeight(122.5);
        dto2.setMushroomName("Not the same mushroom");

        assertNotEquals(dto2, dto);
    }

    @Test
    void testHashCode() {
        dto = new HarvestDetailsDto();
        dto.setId(1L);
        dto.setHarvestDate(new SimpleDateDto(2023,5,12));
        dto.setGrowId(1L);
        dto.setWeight(22.5);
        dto.setMushroomName("Mushroom");

        HarvestDetailsDto dto2 = new HarvestDetailsDto();
        dto2.setId(1L);
        dto2.setHarvestDate(new SimpleDateDto(2023,5,12));
        dto2.setGrowId(1L);
        dto2.setWeight(22.5);
        dto2.setMushroomName("Mushroom");

        assertEquals(dto2.hashCode(), dto.hashCode());
    }

    @Test
    void testHashCodeNotEqual() {
        dto = new HarvestDetailsDto();
        dto.setId(1L);
        dto.setHarvestDate(new SimpleDateDto(2023,5,12));
        dto.setGrowId(1L);
        dto.setWeight(22.5);
        dto.setMushroomName("Mushroom");

        HarvestDetailsDto dto2 = new HarvestDetailsDto();
        dto2.setId(2L);
        dto2.setHarvestDate(new SimpleDateDto(2013,6,20));
        dto2.setGrowId(4L);
        dto2.setWeight(122.5);
        dto2.setMushroomName("Not the same mushroom");

        assertNotEquals(dto2.hashCode(), dto.hashCode());
    }
}