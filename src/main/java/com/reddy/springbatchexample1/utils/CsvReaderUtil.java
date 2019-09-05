package com.reddy.springbatchexample1.utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderUtil {

	public static List<String[]> readAll(Reader reader) {

		CSVParser parser = new CSVParserBuilder().withSeparator(' ').withIgnoreQuotations(true).build();

		CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(parser).build();

		List<String[]> list = new ArrayList<>();
		try {
			list = csvReader.readAll();
			reader.close();
			csvReader.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return list;
	}

	public static List<String[]> oneByOne(Reader reader) {
		List<String[]> list = new ArrayList<>();
		try {
			CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();

			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(parser).build();

			String[] line;
			while ((line = csvReader.readNext()) != null) {
				list.add(line);
			}
			reader.close();
			csvReader.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return list;
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Start");
		// String str =
		// "../spring-batch-example-1/src/main/resources/users.csv";
		String str = "../spring-batch-example-1/src/main/resources/taskletsvschunks/input/Corp_Summary.csv";
		FileReader filereader = new FileReader(str);
		CsvReaderUtil csvReaderExamples = new CsvReaderUtil();
		List<String[]> output = csvReaderExamples.readAll(filereader);
		for (String[] strings : output) {
			System.out.print(5 + " " + strings[5] + " ");
			/*
			 * for (int i = 0; i < strings.length; i++) { System.out.print(i +
			 * " " + strings[i] + " "); }
			 */
			System.out.println();

		}

		System.out.println("DONE");
	}
}