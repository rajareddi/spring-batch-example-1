package com.reddy.springbatchexample1.utils;

public class Person {
	private String firstName;
	private String surname;
	private PersonType type;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public PersonType getType() {
		return type;
	}

	public void setType(PersonType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", surname=" + surname + ", type=" + type + "]";
	}

}
