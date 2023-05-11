package fungeye.cloud.domain.dtos;

import java.util.Objects;

public class MushroomDto {

    private Long id;
    private String name;
    private String description;
    private String origin;
    private int userId;

    public MushroomDto(Long id, String name, String description, String origin, int userId) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setOrigin(origin);
        this.setUserId(userId);
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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
