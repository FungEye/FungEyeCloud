package fungeye.cloud.domain.dtos;


import java.util.List;

public class GrowDto {

    private Long id;
    private DateTimeDto date;
    private String stage;
    private boolean active;
    private Long boxId;
    private List<MushroomDto> mushroomDtoList;



    public GrowDto(Long id, DateTimeDto date, String stage, boolean active, Long boxId, List<MushroomDto> mushroomDtoList) {
        this.id = id;
        this.date = date;
        this.stage = stage;
        this.active = active;
        this.boxId = boxId;
        this.mushroomDtoList = mushroomDtoList;
    }

    public GrowDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTimeDto getDate() {
        return date;
    }

    public void setDate(DateTimeDto date) {
        this.date = date;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public List<MushroomDto> getMushroomDtoList() {
        return mushroomDtoList;
    }

    public void setMushroomDtoList(List<MushroomDto> mushroomDtoList) {
        this.mushroomDtoList = mushroomDtoList;
    }
}
