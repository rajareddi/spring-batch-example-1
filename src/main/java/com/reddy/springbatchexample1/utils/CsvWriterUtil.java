package com.reddy.springbatchexample1.utils;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.nio.file.Path;
import java.util.List;

public class CsvWriterUtil {

	public static String csvWriterOneByOne(List<String[]> stringArray, Path path) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));
			for (String[] array : stringArray) {
				writer.writeNext(array);
			}
			writer.close();
		} catch (Exception ex) {
			Helpers.err(ex);
		}
		return Helpers.readFile(path);
	}

	public static String csvWriterAll(List<String[]> stringArray, Path path) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));
			writer.writeAll(stringArray);
			writer.close();
		} catch (Exception ex) {
			Helpers.err(ex);
		}
		return Helpers.readFile(path);
	}
}