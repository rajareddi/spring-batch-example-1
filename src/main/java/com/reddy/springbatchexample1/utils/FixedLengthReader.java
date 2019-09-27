package com.reddy.springbatchexample1.utils;

//https://www.media-division.com/making-a-fixed-width-text-file-to-csv-converter-in-c-java-php-javascript-and-python/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FixedLengthReader {

	private static final String IN_FILE = "in.txt";
	private static final String OUT_FILE = "out.csv";

	private static final int[][] RANGES = { { 0, 6 }, { 6, 20 }, {26,3},{ 29, 2 } };

	public static void main(String[] args) {
		try {
			BufferedWriter outFile = new BufferedWriter(new FileWriter(new File(OUT_FILE)));

			Path path = new File(IN_FILE).toPath();
			@SuppressWarnings("resource")
			Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
			lines.forEach(line -> {
				List<String> out = new ArrayList<>();

				for (int[] r : RANGES) {
					try {
						out.add(line.toString().substring(r[0], r[0] + r[1]).trim());
					} catch (StringIndexOutOfBoundsException ex) {
						System.out.println(ex);
					}
				}

				try {
					outFile.write(String.join(",", out).concat("\n"));
				} catch (IOException ex) {
					System.out.println("Can't write to " + OUT_FILE);
					System.exit(-1);
				}
			});
			outFile.close();
		} catch (IOException ex) {
			System.out.println("Can't read from " + IN_FILE);
			System.exit(-1);
		}
	}
}