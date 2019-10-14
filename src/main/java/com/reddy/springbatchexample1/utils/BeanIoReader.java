package com.reddy.springbatchexample1.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;
import org.beanio.builder.FixedLengthParserBuilder;
import org.beanio.builder.StreamBuilder;

import com.reddy.springbatchexample1.model.RequestEntity;

public class BeanIoReader {
	public void readCSVFileUsingBeanIo() {
		StreamFactory factory = StreamFactory.newInstance();

		StreamBuilder addRecord = new StreamBuilder("record", "fixedlength").parser(new FixedLengthParserBuilder())
				.addGroup(FileObjectWrapper.class);
		factory.define(addRecord);
		InputStream in = BeanIoReader.class.getClass().getResourceAsStream("/data/TRD_NPOSREQ_CA_YYYYMMDDHHMMSS.txt");
		BeanReader reader = factory.createReader("record", new InputStreamReader(in));
		Object record = null;
		List<RequestEntity> requestEntities = new ArrayList<RequestEntity>();
		// read records from "input.csv"
		while ((record = reader.read()) != null) {

			if ("fileobjectMaper".equals(reader.getRecordName())) {

				FileObjectWrapper header = (FileObjectWrapper) record;
				System.out.println(header.getHeaderRecords().toString());
				System.out.println(header.getRequestEntity().toString());
				requestEntities.addAll(header.getRequestEntity());

			}
		}

		for (RequestEntity requestEntity : requestEntities) {
			System.out.println(requestEntity.getEntity_id());
			System.out.println(requestEntity.getRequest_date());
			
		}
	}

	public static void main(String[] args) {
		new BeanIoReader().readCSVFileUsingBeanIo();
	}
}
