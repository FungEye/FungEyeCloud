package fungeye.cloud.domain.dtos.measured;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MeasuredConditionWithStageDto extends MeasuredConditionDto{

    @Nullable
    private String developmentStage;

    public void setDevelopmentStage(String developmentStage) {
        if (developmentStage.equals("spawn run") ||
                developmentStage.equals("pinning") ||
                developmentStage.equals("fruiting")) {
            this.developmentStage = developmentStage;
        }
        else {
            throw new IllegalArgumentException("Not a valid development stage");
        }
    }
}
