package fungeye.cloud.domain.enities;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoxTest {

    @Test
    public void testGetId() {
        Box box = new Box();
        box.setId(1L);
        assertEquals(Long.valueOf(1), box.getId());
    }

    @Test
    public void testGetGrows() {
        Box box = new Box();
        Grow grow = new Grow();
        Set<Grow> grows = new LinkedHashSet<>();
        grows.add(grow);
        box.setGrows(grows);
        assertEquals(grows, box.getGrows());
    }

    @Test
    public void testGetMeasuredConditions() {
        Box box = new Box();
        MeasuredCondition measuredCondition = new MeasuredCondition();
        Set<MeasuredCondition> measuredConditions = new LinkedHashSet<>();
        measuredConditions.add(measuredCondition);
        box.setMeasuredConditions(measuredConditions);
        assertEquals(measuredConditions, box.getMeasuredConditions());
    }

}
