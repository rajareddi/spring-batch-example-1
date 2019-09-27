package com.reddy.springbatchexample1.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class MainTest {
	public static void main(String[] args) throws IOException {
		/*Path path = Paths.get("in.txt");
		path = Files.createFile(path);
		System.out.println(path.toString());*/
		Date register = new Date();
		String out = String.format("%-10s%-5d%-5d%5tY%<tm%<td", "Raja", 10, 123, register);
		String out1 = String.format("%-10s%-5d%-5d%5tY%<tm%<td", "RajaReddy123456666", 10111, 5678, register);
		System.out.println(out);
		System.out.println(out1);
	}
}
