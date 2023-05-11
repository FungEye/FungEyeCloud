package fungeye.cloud.domain.dtos;


public class MushroomCreationDTO {
    private String name;
    private String description;
    private String origin;

    public MushroomCreationDTO() {
    }

    public MushroomCreationDTO(String name, String description, String origin) {
        this.name = name;
        this.description = description;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "MushroomCreationDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}
