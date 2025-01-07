package com.fileexceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) {

		System.out.println("Current Working Directory (cwd) = " + new File("").getAbsolutePath());
		String filename = "files/testing.csv";
		//testFile2(filename);
		//testFile2(null);

		//File file = new File("/", filename);
		//File file = new File(".", filename);
		File file = new File(new File("").getAbsolutePath(), filename);
		System.out.println(file.getAbsolutePath());
		if(!file.exists()) {
			System.out.println("I can't run unless this file exists.");
			//System.out.println("Quitting Application. Go fix the problem.");
			return;
		}
		System.out.println("I'm good to go.");

		for (File f : File.listRoots()) {
			System.out.println(f);
		}

		Path path = Paths.get("files/testing.csv");
		System.out.println(file.getAbsolutePath());
		if(!Files.exists(path)) {
			System.out.println("2. I can't run unless this file exists.");
			//System.out.println("Quitting Application. Go fix the problem.");
			return;
		}
		System.out.println("2. I'm good to go.");
	}

	private static void testFile(String filename) {

		FileReader reader = null;
		Path path = Paths.get(filename);
		try {
			//List<String> lines = Files.readAllLines(path);
			reader = new FileReader(filename);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			System.out.println("Random log in finally clause after performing try-catch block...");
		}
		System.out.println("File exists and able to use as a resource.");
	}

	private static void testFile2(String filename) {

		try (FileReader reader = new FileReader(filename)) {
		} catch (FileNotFoundException e) {
			System.out.println("File '" + filename + "' does not exist.");
			throw new RuntimeException(e);
		} catch (NullPointerException | IllegalArgumentException badData) {
			System.out.println("User has added bad data " + badData.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			System.out.println("Something unrelated and unexpected happened.");
		} finally {
			System.out.println("Random log in finally clause after performing try-catch block...");
		}
		System.out.println("File exists and able to use as a resource.");
	}
}
