package cn.hxex.springapp.bus;

import java.io.Serializable;

public class Product implements Serializable {

	private int id;
	
	private String description;

	private Double price;

	public void setDescription(String s) {
		description = s;
	}

	public String getDescription() {
		return description;
	}

	public void setPrice(Double d) {
		price = d;
	}

	public Double getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
