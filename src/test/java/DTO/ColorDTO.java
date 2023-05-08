package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColorDTO implements Comparable<ColorDTO> {
    private String color;
    private int year;
    private String name;
    private int id;
    @JsonProperty("pantone_value")
    private String pantoneValue;

    @Override
    public int compareTo(ColorDTO o) {
        return this.year - o.getYear();
    }
}
