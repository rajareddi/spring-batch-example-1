package com.reddy.springbatchexample1.utils;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.builder.Align;

@Record(maxOccurs = 1, name = "requestHeaders")
public class RequestHeaders {
	@Field(length = 10, maxLength = 10, minLength = 0, align = Align.LEFT)
	String filename;
	@Field(length = 27, maxLength = 27, minLength = 0, align = Align.LEFT)
	String genarationDateAndTIme;
	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	String businessDate;
	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	String recordNumber;
	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	String operationalUnit;
	@Field(length = 8, maxLength = 8, minLength = 0, align = Align.LEFT)
	String senderID;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("filename").append(" = ").append(filename).append(";\n").append("genarationDateAndTIme")
				.append(" = ").append(genarationDateAndTIme).append(";\n").append("businessDate").append(" = ")
				.append(businessDate).append(";\n").append("recordNumber").append(" = ").append(recordNumber)
				.append(";\n").append("operationalUnit").append(" = ").append(operationalUnit).append(";\n")
				.append("senderID").append(" = ").append(senderID).append(";\n");
		return builder.toString();
	}

}
