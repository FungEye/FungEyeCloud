package fungeye.cloud.domain.enities;

import fungeye.cloud.domain.enities.users.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MushroomTest {

    Mushroom mushroom;

    @Mock
    UserEntity user;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        mushroom = new Mushroom();
    }

    @Test
    void testSetAndGetId() {
        mushroom.setId(1L);
        assertEquals(1L, mushroom.getId());
    }

    @Test
    void testSetAndGetName() {
        mushroom.setName("Button Mushroom");
        assertEquals("Button Mushroom", mushroom.getName());
    }

    @Test
    void testSetAndGetDescription() {
        mushroom.setDescription("A type of edible mushroom.");
        assertEquals("A type of edible mushroom.", mushroom.getDescription());
    }

    @Test
    void testSetAndGetIdealConditions() {
        IdealCondition idealCondition1 = new IdealCondition();
        IdealCondition idealCondition2 = new IdealCondition();
        Set<IdealCondition> idealConditions = new LinkedHashSet<>();
        idealConditions.add(idealCondition1);
        idealConditions.add(idealCondition2);
        mushroom.setIdealConditions(idealConditions);
        assertEquals(idealConditions, mushroom.getIdealConditions());
    }

    @Test
    void testGetOrigin() {
        mushroom.setOrigin("France");
        assertEquals("France", mushroom.getOrigin());
    }

    @Test
    void testGetUser() {
        mushroom.setUser(user);
        assertEquals(user, mushroom.getUser());
    }
}
