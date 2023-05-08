package fungeye.cloud.domain.dtos;

import java.util.Objects;

public class IdealConditionCreationDto {
    private int mushroomId;
    private String developmentStage;
    private double tempHigh;
    private double tempLow;
    private double humidityHigh;
    private double humidityLow;

    public IdealConditionCreationDto() {
    }

    public int getMushroomId() {
        return mushroomId;
    }

    public void setMushroomId(int mushroomId) {
        this.mushroomId = mushroomId;
    }

    public String getDevelopmentStage() {
        return developmentStage;
    }

    public void setDevelopmentStage(String developmentStage) {
        if (developmentStage.equals("Spawn run") || developmentStage.equals("Pinhead formation") || developmentStage.equals("Fruiting"))
        {
            this.developmentStage = developmentStage;
        }
        else {
            throw new IllegalArgumentException("Your development stage must be Spawn run, Pinhead formation, or Fruiting");
        }
    }

    public double getTempHigh() {
        return tempHigh;
    }

    public void setTempHigh(double tempHigh) {
        this.tempHigh = tempHigh;
    }

    public double getTempLow() {
        return tempLow;
    }

    public void setTempLow(double tempLow) {
        this.tempLow = tempLow;
    }

    public double getHumidityHigh() {
        return humidityHigh;
    }

    public void setHumidityHigh(double humidityHigh) {
        this.humidityHigh = humidityHigh;
    }

    public double getHumidityLow() {
        return humidityLow;
    }

    public void setHumidityLow(double humidityLow) {
        this.humidityLow = humidityLow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdealConditionCreationDto that = (IdealConditionCreationDto) o;
        return mushroomId == that.mushroomId && Double.compare(that.tempHigh, tempHigh) == 0 && Double.compare(that.tempLow, tempLow) == 0 && Double.compare(that.humidityHigh, humidityHigh) == 0 && Double.compare(that.humidityLow, humidityLow) == 0 && Objects.equals(developmentStage, that.developmentStage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mushroomId, developmentStage, tempHigh, tempLow, humidityHigh, humidityLow);
    }

    @Override
    public String toString() {
        return "IdealConditionCreationDto{" +
                "mushroomId=" + mushroomId +
                ", developmentStage='" + developmentStage + '\'' +
                ", tempHigh=" + tempHigh +
                ", tempLow=" + tempLow +
                ", humidityHigh=" + humidityHigh +
                ", humidityLow=" + humidityLow +
                '}';
    }
}
