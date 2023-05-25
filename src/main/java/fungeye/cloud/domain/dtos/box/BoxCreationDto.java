package fungeye.cloud.domain.dtos.box;

import lombok.Data;

@Data
public class BoxCreationDto {
    private String username;
    private String eui;

    public void setEui(String eui) {
        if (eui.length() != 16) {
            throw new IllegalArgumentException("EUI must be exactly 16 characters long");
        } else {
            this.eui = eui;
        }
    }
}
