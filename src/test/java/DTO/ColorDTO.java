package DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ColorDTO{
	private String color;
	private int year;
	private String name;
	private int id;
	@JsonProperty("pantone_value")
	private String pantoneValue;

	public ColorDTO() {
		super();
	}

	public ColorDTO(String color, int year, String name, int id, String pantoneValue) {
		this.color = color;
		this.year = year;
		this.name = name;
		this.id = id;
		this.pantoneValue = pantoneValue;
	}

	public String getColor(){
		return color;
	}

	public int getYear(){
		return year;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getPantoneValue(){
		return pantoneValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ColorDTO colorDTO = (ColorDTO) o;
		return year == colorDTO.year && id == colorDTO.id && Objects.equals(color, colorDTO.color) && name.equals(colorDTO.name) && Objects.equals(pantoneValue, colorDTO.pantoneValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, year, name, id, pantoneValue);
	}

	@Override
	public String toString() {
		return "ColorDTO {" +
				"color='" + color + '\'' +
				", year=" + year +
				", name='" + name + '\'' +
				", id=" + id +
				", pantoneValue='" + pantoneValue + '\'' +
				'}';
	}
}
