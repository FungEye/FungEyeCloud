package fungeye.cloud.domain.enities;

import fungeye.cloud.domain.enities.users.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;

class BoxTest {
    private Box box;

    @Mock
    private UserEntity user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        box = new Box();
    }

    @Test
    void testGetId() {
        Box box = new Box();
        box.setId(1L);
        assertEquals(Long.valueOf(1), box.getId());
    }

    @Test
    void testGetGrows() {
        Box box = new Box();
        Grow grow = new Grow();
        Set<Grow> grows = new LinkedHashSet<>();
        grows.add(grow);
        box.setGrows(grows);
        assertEquals(grows, box.getGrows());
    }

    @Test
    void testGetMeasuredConditions() {
        Box box = new Box();
        MeasuredCondition measuredCondition = new MeasuredCondition();
        Set<MeasuredCondition> measuredConditions = new LinkedHashSet<>();
        measuredConditions.add(measuredCondition);
        box.setMeasuredConditions(measuredConditions);
        assertEquals(measuredConditions, box.getMeasuredConditions());
    }

    @Test
    void getUserEntity() {
        box.setUserEntity(user);
        assertEquals(user, box.getUserEntity());
    }

    @Test
    void setUserEntity() {
        user.getBoxes();
        box.setUserEntity(user);
        verify(user).getBoxes();
    }

    @Test
    public void testEqualsWhenObjectsAreEqual() {
        Box box1 = new Box();
        box1.setId(1L);
        Box box2 = new Box();
        box2.setId(1L);
        assertEquals(box1, box2);
    }

    @Test
    public void testEqualsWhenObjectsAreNotEqual() {
        Box box1 = new Box();
        box1.setId(1L);
        Box box2 = new Box();
        box2.setId(2L);
        assertNotEquals(box1, box2);
    }

    @Test
    public void testEqualsWhenComparedToNull() {
        Box box1 = new Box();
        box1.setId(1L);
        assertNotEquals(null, box1);
    }

    @Test
    public void testEqualsWhenComparedToDifferentClass() {
        Box box1 = new Box();
        box1.setId(1L);
        UserEntity user = new UserEntity();
        assertNotEquals(box1, user);
    }
}
