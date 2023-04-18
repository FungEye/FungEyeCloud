package fungeye.cloud.domain.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchConditionsParamTest {

    @Test
    public void testGettersAndSetters() {
        SearchConditionsParam param = new SearchConditionsParam();
        param.setDay(1);
        param.setMonth(2);
        param.setYear(2022);
        param.setHour(12);
        param.setMinute(30);
        param.setId(12345L);

        assertEquals(1, param.getDay());
        assertEquals(2, param.getMonth());
        assertEquals(2022, param.getYear());
        assertEquals(12, param.getHour());
        assertEquals(30, param.getMinute());
        assertEquals(12345L, param.getId());
    }
}
