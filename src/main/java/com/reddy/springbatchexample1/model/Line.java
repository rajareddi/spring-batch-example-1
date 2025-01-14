package com.reddy.springbatchexample1.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Line implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private LocalDate dob;
	private Long age;
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Line(String name, LocalDate dob) {
		this.name = name;
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(this.name);
		sb.append(",");
		sb.append(this.dob.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		if (this.age != null) {
			sb.append(",");
			sb.append(this.age);
		}
		sb.append(",");
		sb.append(this.date);
		sb.append("]");
		return sb.toString();
	}
}