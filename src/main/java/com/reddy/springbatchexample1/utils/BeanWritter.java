package com.reddy.springbatchexample1.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.beanio.builder.StreamBuilder;

public class BeanWritter {
	public void readCSVFileUsingBeanIo() {
		StreamFactory factory = StreamFactory.newInstance();
		factory.define(new StreamBuilder("fix", "fixedlength")
				.addRecord(Header.class)
				.addRecord(Person2.class));
		File fi = new File("/data/person_out.txt");
		if (fi.isFile()) {
			System.out.println(fi.getAbsoluteFile());
		}

		BeanWriter beanWriter = factory.createWriter("fix", fi);
		List<Person2> persons = new ArrayList<Person2>();
		Person2 p1 = new Person2();
		p1.setFirstName("RajaReddy");
		p1.setSurname("karrem");
		p1.setType(PersonType.MALE);

		Person2 p11 = new Person2();
		p11.setFirstName("Mounisha");
		p11.setSurname("Gaddam");
		p11.setType(PersonType.FAMILY);
		persons.add(p1);
		persons.add(p11);
		Header header = new Header();
		header.setHeader("RESPONSEQ");
		header.setDate(new Date().toGMTString());
		beanWriter.write(header);
		beanWriter.write(p1);
		beanWriter.write(p11);
		beanWriter.flush();
		beanWriter.close();
	}

	public static void main(String[] args) {
		new BeanWritter().readCSVFileUsingBeanIo();
	}
}
