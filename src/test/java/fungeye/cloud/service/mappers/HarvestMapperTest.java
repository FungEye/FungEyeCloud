package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.HarvestCreationDto;
import fungeye.cloud.domain.dtos.HarvestDetailsDto;
import fungeye.cloud.domain.dtos.SimpleDateDto;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Harvest;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.persistence.repository.GrowRepository;
import fungeye.cloud.persistence.repository.MushroomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class HarvestMapperTest {

    private Harvest harvest;
    private HarvestCreationDto create;
    private HarvestDetailsDto details;


    @Mock
    MushroomRepository mushroomRepo;
    @Mock
    GrowRepository growRepo;

    @Mock
    Grow grow;

    @Mock
    Mushroom mushroom;

    HarvestMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mushroom = new Mushroom();
        mushroom.setName("Wohooo, correct mushroom!");

        grow = new Grow();
        grow.setId(1L);
        grow.setMushroom(mushroom);

        harvest = new Harvest();
        harvest.setDateHarvested(LocalDate.now());
        harvest.setGrow(grow);
        harvest.setMushroom(mushroom);
        harvest.setId(1L);
        harvest.setWeight(22.5);

        create = new HarvestCreationDto();
        create.setHarvestDate(new SimpleDateDto(
                LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()));
        create.setGrowId(1L);
        create.setWeight(22.5);

        details = new HarvestDetailsDto();
        details.setId(1L);
        details.setWeight(22.5);
        details.setHarvestDate(new SimpleDateDto(
                LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()));
        details.setGrowId(1L);
        details.setMushroomName("Wohooo, correct mushroom!");



        mapper = new HarvestMapper(mushroomRepo, growRepo);

    }

    @Test
    void testMapCreationDtoToEntity() {
        when(growRepo.getReferenceById(1L)).thenReturn(grow);
        when(growRepo.findById(1L)).thenReturn(Optional.of(grow));
        when(mushroomRepo.getReferenceById(any())).thenReturn(mushroom);
        assertEquals(harvest.getMushroom().getName(), mapper.mapCreationDtoToEntity(create).getMushroom().getName());
        assertEquals(harvest.getDateHarvested(), mapper.mapCreationDtoToEntity(create).getDateHarvested());
        assertEquals(harvest.getGrow().getId(), mapper.mapCreationDtoToEntity(create).getGrow().getId());
        assertEquals(harvest.getWeight(), mapper.mapCreationDtoToEntity(create).getWeight());
    }

    @Test
    void testMapEntityToDetailsDto() {
        assertEquals(details, mapper.mapEntityToDetailsDto(harvest));
    }
}