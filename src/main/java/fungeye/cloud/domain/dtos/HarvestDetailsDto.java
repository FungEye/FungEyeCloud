package fungeye.cloud.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link fungeye.cloud.domain.enities.Harvest} entity
 */
public class HarvestDetailsDto extends HarvestCreationDto implements Serializable {
    private Long id;
    @Size(max = 255)
    @NotNull
    private String mushroomName;
    private Long growId;
    private Double weight;
    private SimpleDateDto harvestDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMushroomName() {
        return mushroomName;
    }

    public void setMushroomName(String mushroomName) {
        this.mushroomName = mushroomName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HarvestDetailsDto that = (HarvestDetailsDto) o;
        return Objects.equals(id, that.id) && Objects.equals(mushroomName, that.mushroomName) && Objects.equals(growId, that.growId) && Objects.equals(weight, that.weight) && Objects.equals(harvestDate, that.harvestDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mushroomName, growId, weight, harvestDate);
    }
}