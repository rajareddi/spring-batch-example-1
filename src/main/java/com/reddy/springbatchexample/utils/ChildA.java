package com.reddy.springbatchexample.utils;

import java.util.List;

public class ChildA extends ParentA {

	String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ChildA() {
		System.out.println("Default");
	}

	public ChildA(String a, int b) {
		super(a, b);
		System.out.println("arg");

	}

	public List<String> getList() {
		return super.getList();
	}
}
