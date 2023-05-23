package fungeye.cloud.domain.dtos.measured;

import lombok.Data;

@Data
public class SearchConditionsParam {
    private Integer day;
    private Integer month;
    private Integer year;
    private Integer hour;
    private Integer minute;
    private Long id;
}
