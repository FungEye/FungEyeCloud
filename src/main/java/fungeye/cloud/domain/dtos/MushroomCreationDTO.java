package fungeye.cloud.domain.dtos;


public class MushroomCreationDTO {
    private String name;
    private String description;

    public MushroomCreationDTO() {
    }

    public MushroomCreationDTO(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "MushroomCreationDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}