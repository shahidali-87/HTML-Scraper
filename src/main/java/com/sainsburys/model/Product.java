package com.sainsburys.model;

import java.math.BigDecimal;
import java.util.OptionalInt;

public class Product {
	
	private String title;
	private BigDecimal unit_price;
	private OptionalInt kcal_per_100g;
	private String description;
	
	public Product(String title, BigDecimal unit_price, OptionalInt kcal_per_100g, String description) {
		this.title = title;
		this.unit_price = unit_price;
		this.kcal_per_100g = kcal_per_100g;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(BigDecimal unit_price) {
		this.unit_price = unit_price;
	}

	public OptionalInt getKcal_per_100g() {
		return kcal_per_100g;
	}

	public void setKcal_per_100g(OptionalInt kcal_per_100g) {
		this.kcal_per_100g = kcal_per_100g;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Product [title=" + title + ", unit_price=" + unit_price + ", kcal_per_100g=" + kcal_per_100g
				+ ", description=" + description + "]";
	}

}
