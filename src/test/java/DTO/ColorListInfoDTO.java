package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColorListInfoDTO {
    @JsonProperty("per_page")
    private int perPage;
    private int total;
    private int page;
    @JsonProperty("total_pages")
    private int totalPages;

    public ColorListInfoDTO() {
        super();
    }

    public ColorListInfoDTO(int perPage, int total, int page, int totalPages) {
        this.perPage = perPage;
        this.total = total;
        this.page = page;
        this.totalPages = totalPages;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    @Override
    public String toString() {
        return "ColorListInfoDTO{" +
                "perPage=" + perPage +
                ", total=" + total +
                ", page=" + page +
                ", totalPages=" + totalPages +
                '}';
    }
}
