package com.pathlistings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class Main {

	public static void main(String[] args) {

		Path path = Path.of("multiple/directories/files/testing.txt");
		//printPathInfo(path);
		logStatement(path);
		extraInfo(path);
	}

	private static void printPathInfo(Path path) {

		System.out.println("Path: " + path);
		System.out.println("fileName = " + path.getFileName());
		System.out.println("parent = " + path.getParent());
		Path absolutePath = path.toAbsolutePath();
		System.out.println("Absolute Path: = " + absolutePath);
		System.out.println("Absolute Path Root: = " + absolutePath.getRoot());
		System.out.println("Root = " + path.getRoot());
		System.out.println("isAbsolute = " + path.isAbsolute());

		System.out.println(absolutePath.getRoot());
		//int i = 1;
		//var iterator = path.toAbsolutePath().iterator();
		//while (iterator.hasNext()) {
		//	System.out.println(".".repeat(i++) + " " + iterator.next());
		//}

		int pathParts = absolutePath.getNameCount();
		for (int i = 0; i < pathParts; i++) {
			System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
		}
		System.out.println("-".repeat(55));
	}

	private static void logStatement(Path path) {

		try {
			Path parent = path.getParent();
			if (!Files.exists(parent)) {
				//Files.createDirectory(parent);
				Files.createDirectories(parent);
			}
			Files.writeString(path, Instant.now() + ": hello file world\n", StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-".repeat(55));
	}

	private static void extraInfo(Path path) {

		try {
			var attributes = Files.readAttributes(path, "*");
			attributes.entrySet().forEach(System.out::println);
			System.out.println(Files.probeContentType(path));
		} catch (IOException e) {
			System.out.println("Problem getting attributes");
		}
		System.out.println("-".repeat(55));
	}
}
