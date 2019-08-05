package com.reddy.springbatchexample.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParentA extends AParentA {

	String a;
	int b;
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	List<String> list = new ArrayList<>(Arrays.asList("Raja", "Reddy"));

	public ParentA() {

	}

	public ParentA(String a, int b) {
		this.a = a;
		this.b = b;

	}

	public List<String> getList() {
		return list;

	}

}