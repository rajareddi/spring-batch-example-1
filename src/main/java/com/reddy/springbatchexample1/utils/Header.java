package com.reddy.springbatchexample1.utils;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

@Record
public class Header {
	
	@Field(length=15)
	String header;
	@Field(length=15)
	String date;
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
