package fungeye.cloud.domain.dtos;

import java.util.Objects;

public class IdealConditionDto {
    private Long mushroomId;
    private String developmentStage;
    private double tempHigh;
    private double tempLow;
    private double humidityHigh;
    private double humidityLow;
    private double co2High;
    private double co2Low;
    private double lightHigh;
    private double lightLow;

    public Long getMushroomId() {
        return mushroomId;
    }

    public void setMushroomId(Long mushroomId) {
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
    public double getCo2High() {
        return co2High;
    }

    public void setCo2High(double co2High) {
        this.co2High = co2High;
    }

    public double getCo2Low() {
        return co2Low;
    }

    public void setCo2Low(double co2Low) {
        this.co2Low = co2Low;
    }

    public double getLightHigh() {
        return lightHigh;
    }

    public void setLightHigh(double lightHigh) {
        this.lightHigh = lightHigh;
    }

    public double getLightLow() {
        return lightLow;
    }

    public void setLightLow(double lightLow) {
        this.lightLow = lightLow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdealConditionDto that = (IdealConditionDto) o;
        return mushroomId == that.mushroomId &&
                Double.compare(that.tempHigh, tempHigh) == 0 &&
                Double.compare(that.tempLow, tempLow) == 0 &&
                Double.compare(that.humidityHigh, humidityHigh) == 0 &&
                Double.compare(that.humidityLow, humidityLow) == 0 &&
                Double.compare(that.co2High, co2High) == 0 &&
                Double.compare(that.lightHigh, lightHigh) == 0 &&
                Double.compare(that.lightLow, lightLow) == 0 &&
                Objects.equals(developmentStage, that.developmentStage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mushroomId, developmentStage, tempHigh, tempLow, humidityHigh, humidityLow, co2High, co2Low, lightHigh, lightLow);
    }

    @Override
    public String toString() {
        return "IdealConditionDto{" +
                "mushroomId=" + mushroomId +
                ", developmentStage='" + developmentStage + '\'' +
                ", tempHigh=" + tempHigh +
                ", tempLow=" + tempLow +
                ", humidityHigh=" + humidityHigh +
                ", humidityLow=" + humidityLow +
                ", co2High=" + co2High +
                ", co2Low=" + co2Low +
                ", lightHigh=" + lightHigh +
                ", lightLow=" + lightLow +
                '}';
    }
}
