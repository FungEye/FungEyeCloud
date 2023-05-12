package fungeye.cloud.domain.dtos;


import java.io.Serializable;
import java.util.Objects;


public class HarvestCreationDto implements Serializable {
    private Long growId;
    private Double weight;
    private SimpleDateDto harvestDate;


    public Long getGrowId() {
        return growId;
    }

    public void setGrowId(Long growId) {
        this.growId = growId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public SimpleDateDto getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(SimpleDateDto harvestDate) {
        this.harvestDate = harvestDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HarvestCreationDto that = (HarvestCreationDto) o;
        return Objects.equals(growId, that.growId) && Objects.equals(weight, that.weight) && Objects.equals(harvestDate, that.harvestDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(growId, weight, harvestDate);
    }
}