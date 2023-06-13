package dto.color;

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
    @JsonProperty("color")
    private String color;
    @JsonProperty("year")
    private int year;
    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private int id;
    @JsonProperty("pantone_value")
    private String pantoneValue;

    @Override
    public int compareTo(ColorDTO o) {
        return this.year - o.getYear();
    }
}
