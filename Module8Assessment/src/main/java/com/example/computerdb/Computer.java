package com.example.computerdb;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Computer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String monitor;
	private String mouse;
	private String keyboard;
	private Boolean forGaming;
	private String brand;
	private Boolean laptop;
	private String model;
	private Double price;

	public Computer(String monitor, String mouse, String keyboard, boolean forGaming) {
		this.monitor = monitor;
		this.mouse = mouse;
		this.keyboard = keyboard;
		this.forGaming = forGaming;
	}

	public Computer() {
		// Default constructor
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getMouse() {
		return mouse;
	}

	public void setMouse(String mouse) {
		this.mouse = mouse;
	}

	public String getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(String keyboard) {
		this.keyboard = keyboard;
	}

	public Boolean getForGaming() {
		return forGaming;
	}

	public void setForGaming(Boolean forGaming) {
		this.forGaming = forGaming;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Boolean getLaptop() {
		return laptop;
	}

	public void setLaptop(Boolean laptop) {
		this.laptop = laptop;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Computer computer = (Computer) o;
		return Objects.equals(id, computer.id) && Objects.equals(monitor, computer.monitor)
				&& Objects.equals(mouse, computer.mouse) && Objects.equals(keyboard, computer.keyboard)
				&& Objects.equals(forGaming, computer.forGaming) && Objects.equals(brand, computer.brand)
				&& Objects.equals(laptop, computer.laptop) && Objects.equals(model, computer.model)
				&& Objects.equals(price, computer.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, monitor, mouse, keyboard, forGaming, brand, laptop, model, price);
	}

	@Override
	public String toString() {
		return "Computer{" + "id=" + id + ", monitor='" + monitor + '\'' + ", mouse='" + mouse + '\'' + ", keyboard='"
				+ keyboard + '\'' + ", forGaming=" + forGaming + ", brand='" + brand + '\'' + ", laptop=" + laptop
				+ ", model='" + model + '\'' + ", price=" + price + '}';
	}
}