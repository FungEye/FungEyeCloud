package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Mushroom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MushroomMapperTest {

    private static final Long MUSHROOM_ID = 1L;
    private static final String MUSHROOM_NAME = "Shiitake";
    private static final String MUSHROOM_DESC = "Large, dark-brown mushroom with an earthy flavor";
    private static final Long GROW_ID = 2L;

    private MushroomMapper mushroomMapper;

    @Mock
    private Mushroom mushroomMock;

    @Mock
    private Grow growMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mushroomMapper = new MushroomMapper();
    }

    @Test
    void testMapToMushroomDto() {
        when(mushroomMock.getId()).thenReturn(MUSHROOM_ID);
        when(mushroomMock.getName()).thenReturn(MUSHROOM_NAME);
        when(mushroomMock.getDescription()).thenReturn(MUSHROOM_DESC);
        when(mushroomMock.getGrow()).thenReturn(growMock);
        when(growMock.getId()).thenReturn(GROW_ID);

        MushroomDto mushroomDto = MushroomMapper.mapToMushroomDto(mushroomMock);

        assertEquals(MUSHROOM_ID, mushroomDto.getId());
        assertEquals(MUSHROOM_NAME, mushroomDto.getName());
        assertEquals(MUSHROOM_DESC, mushroomDto.getDescription());
        assertEquals(GROW_ID, mushroomDto.getGrowId());
    }

    @Test
    void testMapToMushroomDtoList() {
        Set<Mushroom> mushroomSet = new HashSet<>();
        mushroomSet.add(mushroomMock);

        when(mushroomMock.getId()).thenReturn(MUSHROOM_ID);
        when(mushroomMock.getName()).thenReturn(MUSHROOM_NAME);
        when(mushroomMock.getDescription()).thenReturn(MUSHROOM_DESC);
        when(mushroomMock.getGrow()).thenReturn(growMock);
        when(growMock.getId()).thenReturn(GROW_ID);

        List<MushroomDto> mushroomDtoList = MushroomMapper.mapToMushroomDtoList(mushroomSet);

        assertEquals(1, mushroomDtoList.size());

        MushroomDto mushroomDto = mushroomDtoList.get(0);

        assertEquals(MUSHROOM_ID, mushroomDto.getId());
        assertEquals(MUSHROOM_NAME, mushroomDto.getName());
        assertEquals(MUSHROOM_DESC, mushroomDto.getDescription());
        assertEquals(GROW_ID, mushroomDto.getGrowId());
    }

    @Test
    void testMapToMushroomDtoListEmpty() {
        Set<Mushroom> mushroomSet = Collections.emptySet();

        List<MushroomDto> mushroomDtoList = MushroomMapper.mapToMushroomDtoList(mushroomSet);

        assertEquals(0, mushroomDtoList.size());
    }

}
