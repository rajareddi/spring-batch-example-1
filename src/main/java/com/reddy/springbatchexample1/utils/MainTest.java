package com.reddy.springbatchexample1.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainTest {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("response.txt");
		path = Files.createFile(path);
		System.out.println(path.toString());
	}
}
