package dto.color;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ColorListInfoDTOIgnore {
    @JsonProperty("per_page")
    private int perPage;
    private int total;
    private int page;
    @JsonProperty("total_pages")
    private int totalPages;
}
