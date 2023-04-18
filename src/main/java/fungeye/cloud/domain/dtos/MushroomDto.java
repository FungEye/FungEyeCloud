package fungeye.cloud.domain.dtos;

public class MushroomDto {

    private Long id;
    private String name;
    private String description;
    private Long growId;

    public MushroomDto(Long id, String name, String description, Long growId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.growId = growId;
    }

    public MushroomDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getGrowId() {
        return growId;
    }

    public void setGrowId(Long growId) {
        this.growId = growId;
    }
}
