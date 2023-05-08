package DTO;

public class ColorListInfoDTOBuilder {
    private int perPage;
    private int total;
    private int page;
    private int totalPages;

    public ColorListInfoDTOBuilder setPerPage(int perPage) {
        this.perPage = perPage;
        return this;
    }

    public ColorListInfoDTOBuilder setTotal(int total) {
        this.total = total;
        return this;
    }

    public ColorListInfoDTOBuilder setPage(int page) {
        this.page = page;
        return this;
    }

    public ColorListInfoDTOBuilder setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public ColorListInfoDTO build() {
        return new ColorListInfoDTO(perPage, total, page, totalPages);
    }
}