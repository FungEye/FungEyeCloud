package fungeye.cloud.service.mappers;

import fungeye.cloud.domain.dtos.MushroomCreationDTO;
import fungeye.cloud.domain.dtos.MushroomDto;
import fungeye.cloud.domain.enities.Grow;
import fungeye.cloud.domain.enities.Mushroom;
import fungeye.cloud.domain.enities.users.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class MushroomMapperTest {

    private static final Long MUSHROOM_ID = 1L;
    private static final String MUSHROOM_NAME = "Shiitake";
    private static final String MUSHROOM_DESC = "Large, dark-brown mushroom with an earthy flavor";
    private static final String MUSHROOM_ORIGIN = "Japan";
    private static final int USER_ID = 1;

    private Mushroom mushroom;
    private UserEntity user;


    @BeforeEach
    void setUp() {
        mushroom = new Mushroom();
        mushroom.setId(MUSHROOM_ID);
        mushroom.setName(MUSHROOM_NAME);
        mushroom.setDescription(MUSHROOM_DESC);
        mushroom.setOrigin(MUSHROOM_ORIGIN);
        user = new UserEntity();
        user.setId(USER_ID);
        mushroom.setUser(user);
    }

    @Test
    void testPrivateConstructor() throws NoSuchMethodException {
        Constructor<MushroomMapper> constructor = MushroomMapper.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    void testMapToMushroomDto() {
        MushroomDto mushroomDto = MushroomMapper.mapToMushroomDto(mushroom);

        assertEquals(MUSHROOM_ID, mushroomDto.getId());
        assertEquals(MUSHROOM_NAME, mushroomDto.getName());
        assertEquals(MUSHROOM_DESC, mushroomDto.getDescription());
        assertEquals(MUSHROOM_ORIGIN, mushroomDto.getOrigin());
        assertEquals(USER_ID, mushroomDto.getUserId());
    }

    @Test
    void testMapToMushroomDtoList() {
        Set<Mushroom> mushroomSet = new HashSet<>();
        mushroomSet.add(mushroom);

        List<MushroomDto> mushroomDtoList = MushroomMapper.mapToMushroomDtoList(mushroomSet);

        assertEquals(1, mushroomDtoList.size());

        MushroomDto mushroomDto = mushroomDtoList.get(0);

        assertEquals(MUSHROOM_ID, mushroomDto.getId());
        assertEquals(MUSHROOM_NAME, mushroomDto.getName());
        assertEquals(MUSHROOM_DESC, mushroomDto.getDescription());
    }

    @Test
    void testMapToMushroomDtoListEmpty() {
        Set<Mushroom> mushroomSet = Collections.emptySet();

        List<MushroomDto> mushroomDtoList = MushroomMapper.mapToMushroomDtoList(mushroomSet);

        assertEquals(0, mushroomDtoList.size());
    }

    @Test
    void testMapCreateToMushroom() {
        MushroomCreationDTO dto = new MushroomCreationDTO();
        dto.setName("Button Mushroom");
        dto.setDescription("A common mushroom");

        Mushroom mushroom = MushroomMapper.mapCreateToMushroom(dto);

        assertEquals(dto.getName(), mushroom.getName());
        assertEquals(dto.getDescription(), mushroom.getDescription());
    }
}
