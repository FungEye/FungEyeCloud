package fungeye.cloud.domain.dtos;

import java.util.Objects;

public class MushroomDto extends MushroomCreationDTO{

    private Long id;
    private String name;
    private String description;
    private String origin;
    private int userId;


    public MushroomDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MushroomDto that = (MushroomDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(origin, that.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, origin);
    }

    @Override
    public String toString() {
        return "MushroomDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", origin='" + origin + '\'' +
                ", userId=" + userId +
                '}';
    }
}
