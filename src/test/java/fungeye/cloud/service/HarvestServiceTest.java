package fungeye.cloud.service;

import fungeye.cloud.domain.dtos.HarvestCreationDto;
import fungeye.cloud.domain.dtos.HarvestDetailsDto;
import fungeye.cloud.domain.dtos.SimpleDateDto;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Harvest;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.persistence.repository.HarvestRepository;
import fungeye.cloud.service.mappers.HarvestMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

class HarvestServiceTest {
    private HarvestService service;
    @Mock
    private HarvestRepository repository;
    @Mock
    private HarvestMapper mapper;

    private Mushroom mushroom;

   private Grow grow;

    private Harvest inProgress;
    private Harvest harvest;
    private Harvest harvest2;
    private Harvest harvest3;
    private HarvestCreationDto create;
    private HarvestDetailsDto details1;
    private HarvestDetailsDto details2;
    private HarvestDetailsDto details3;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.service = new HarvestService(repository, mapper);

        mushroom = new Mushroom();
        mushroom.setName("MUSHROOM");

        grow = new Grow();
        grow.setId(1L);

        create = new HarvestCreationDto();
        create.setGrowId(1L);
        create.setHarvestDate(new SimpleDateDto(
                LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()
        ));
        create.setWeight(100.0);

        harvest = new Harvest();
        harvest.setId(1L);
        harvest.setDateHarvested(LocalDate.now());
        harvest.setMushroom(mushroom);
        harvest.setGrow(grow);
        harvest.setWeight(100.0);

        inProgress = new Harvest();
        inProgress.setDateHarvested(LocalDate.now());
        inProgress.setMushroom(mushroom);
        inProgress.setGrow(grow);
        inProgress.setWeight(100.0);

        details1 = new HarvestDetailsDto();
        details1.setId(1L);
        details1.setHarvestDate(new SimpleDateDto(
                LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()
        ));
        details1.setWeight(100.0);
        details1.setGrowId(1L);
        details1.setMushroomName("MUSHROOM");
    }

    @Test
    void addHarvest() {
        when(mapper.mapCreationDtoToEntity(create)).thenReturn(inProgress);
        when(repository.save(inProgress)).thenReturn(harvest);
        when(mapper.mapEntityToDetailsDto(harvest)).thenReturn(details1);

        Assertions.assertEquals(details1, service.addHarvest(create));
    }

    @Test
    void getAllHarvestsByUserIdWhenHarvestsArePresent() {
        details2 = new HarvestDetailsDto();
        details2.setId(2L);
        details2.setHarvestDate(new SimpleDateDto(
                LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()
        ));
        details2.setWeight(200.0);
        details2.setGrowId(1L);
        details2.setMushroomName("MUSHROOM");

        details3 = new HarvestDetailsDto();
        details3.setId(3L);
        details3.setHarvestDate(new SimpleDateDto(
                LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()
        ));
        details3.setWeight(300.0);
        details3.setGrowId(1L);
        details3.setMushroomName("MUSHROOM");

        harvest2 = new Harvest();
        harvest2.setId(2L);
        harvest2.setDateHarvested(LocalDate.now());
        harvest2.setMushroom(mushroom);
        harvest2.setGrow(grow);
        harvest2.setWeight(200.0);

        harvest3 = new Harvest();
        harvest3.setId(3L);
        harvest3.setDateHarvested(LocalDate.now());
        harvest3.setMushroom(mushroom);
        harvest3.setGrow(grow);
        harvest3.setWeight(300.0);

        List<Harvest> harvests = new ArrayList<>();
        harvests.add(harvest);
        harvests.add(harvest2);
        harvests.add(harvest3);

        List<HarvestDetailsDto> result = new ArrayList<>();
        result.add(details1);
        result.add(details2);
        result.add(details3);

        String username = "john";

        when(repository.findByGrow_Box_UserEntity_UsernameOrderByDateHarvestedDesc(username)).thenReturn(harvests);
        when(mapper.mapEntityToDetailsDto(harvest)).thenReturn(details1);
        when(mapper.mapEntityToDetailsDto(harvest2)).thenReturn(details2);
        when(mapper.mapEntityToDetailsDto(harvest3)).thenReturn(details3);

        Assertions.assertEquals(result, service.getAllHarvestsByUsername(username));
    }

    @Test
    void getAllHarvestsByUserIdWhenNoHarvestsArePresent() {
        String username = "john";
        when(repository.findByGrow_Box_UserEntity_UsernameOrderByDateHarvestedDesc(username))
                .thenReturn(new ArrayList<>());

        Assertions.assertThrows(NoSuchElementException.class, () -> service.getAllHarvestsByUsername(username));
    }

    @Test
    void testGetAllHarvestsByGrowId() {
        details2 = new HarvestDetailsDto();
        details2.setId(2L);
        details2.setHarvestDate(new SimpleDateDto(
                LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()
        ));
        details2.setWeight(200.0);
        details2.setGrowId(1L);
        details2.setMushroomName("MUSHROOM");

        details3 = new HarvestDetailsDto();
        details3.setId(3L);
        details3.setHarvestDate(new SimpleDateDto(
                LocalDate.now().getYear(), LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()
        ));
        details3.setWeight(300.0);
        details3.setGrowId(1L);
        details3.setMushroomName("MUSHROOM");

        harvest2 = new Harvest();
        harvest2.setId(2L);
        harvest2.setDateHarvested(LocalDate.now());
        harvest2.setMushroom(mushroom);
        harvest2.setGrow(grow);
        harvest2.setWeight(200.0);

        harvest3 = new Harvest();
        harvest3.setId(3L);
        harvest3.setDateHarvested(LocalDate.now());
        harvest3.setMushroom(mushroom);
        harvest3.setGrow(grow);
        harvest3.setWeight(300.0);

        List<Harvest> harvests = new ArrayList<>();
        harvests.add(harvest);
        harvests.add(harvest2);
        harvests.add(harvest3);

        List<HarvestDetailsDto> result = new ArrayList<>();
        result.add(details1);
        result.add(details2);
        result.add(details3);

        when(repository.findByGrow_IdOrderByDateHarvestedDesc(1L)).thenReturn(harvests);
        when(mapper.mapEntityToDetailsDto(harvest)).thenReturn(details1);
        when(mapper.mapEntityToDetailsDto(harvest2)).thenReturn(details2);
        when(mapper.mapEntityToDetailsDto(harvest3)).thenReturn(details3);

        Assertions.assertEquals(result, service.getAllHarvestsByGrowId(1L));
    }
}