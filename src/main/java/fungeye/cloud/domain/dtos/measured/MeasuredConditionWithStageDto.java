package fungeye.cloud.domain.dtos.measured;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class MeasuredConditionWithStageDto extends MeasuredConditionDto {

    private Long growId;
    @Nullable
    private String developmentStage;

    public void setDevelopmentStage(String developmentStage) {
        if (developmentStage.equals("spawn run") ||
                developmentStage.equals("pinning") ||
                developmentStage.equals("fruiting") ||
                developmentStage.equals("")) {
            this.developmentStage = developmentStage;
        } else {
            throw new IllegalArgumentException("Not a valid development stage");
        }
    }
}
