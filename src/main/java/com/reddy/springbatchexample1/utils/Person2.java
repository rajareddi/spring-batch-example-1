package com.reddy.springbatchexample1.utils;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

//https://www.javatips.net/api/beanio-master/test/org/beanio/parser/annotation/AnnotationTest.javas
//https://www.codota.com/code/java/methods/org.beanio.StreamFactory/createWriter
//http://beanio.org/
//https://www.javaboss.it/primi-passi-con-beanio-parte-3/?doing_wp_cron=1569806495.1350870132446289062500
//https://www.programcreek.com/java-api-examples/?code=jpretori%2FSoaringCoach%2FSoaringCoach-master%2Fsrc%2Ftest%2Fjava%2Fsoaringcoach%2Fanalysis%2FTestFileParsing.java#
////https://github.com/Jayadeep2308/FlatFileParser
///https://docs.spring.io/spring-batch/2.0.x/reference/html/readersAndWriters.html
//https://stackoverflow.com/questions/51383474/minoccurs-attribute-in-group-annotation-causes-unexpectedrecordexception

@Record
public class Person2 {
	@Field(length = 15)
	private String firstName;
	@Field(length = 15)
	private String surname;
	@Field(length = 15)
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
