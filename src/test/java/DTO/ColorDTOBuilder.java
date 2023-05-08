package DTO;

public class ColorDTOBuilder {
    private String color;
    private int year;
    private String name;
    private int id;
    private String pantoneValue;

    public ColorDTOBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public ColorDTOBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public ColorDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ColorDTOBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ColorDTOBuilder setPantoneValue(String pantoneValue) {
        this.pantoneValue = pantoneValue;
        return this;
    }

    public ColorDTO build() {
        return new ColorDTO(color, year, name, id, pantoneValue);
    }
}