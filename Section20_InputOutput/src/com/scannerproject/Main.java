package com.scannerproject;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(new File("fixedWidth.txt"))) {
			//while (sc.hasNextLine()) {
			//	System.out.println(sc.nextLine());
			//}
			//System.out.println(sc.delimiter());
			//sc.useDelimiter("$");	// $ = meta character for end of line
			//sc.tokens().forEach(System.out::println); // tokens() method is used to get a stream of delimiter separated
													  // tokens from the Scanner object
			//sc.findAll("[A-Za-z]{10,}")
			//		.map(MatchResult::group)
			//		.distinct()
			//		.sorted()
			//		.forEach(System.out::println);

			var results = sc.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
					.skip(1)
					.map(m -> m.group(3).trim())
					.distinct()
					.sorted()
					.toArray(String[]::new);
			System.out.println(Arrays.toString(results));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		try (Scanner sc = new Scanner(Path.of("fixedWidth.txt"))) {
			//while (sc.hasNextLine()) {
			//	System.out.println(sc.nextLine());
			//}
			//System.out.println(sc.delimiter());
			//sc.useDelimiter("$");	// $ = meta character for end of line
			//sc.tokens().forEach(System.out::println); // tokens() method is used to get a stream of delimiter separated
			// tokens from the Scanner object
			//sc.findAll("[A-Za-z]{10,}")
			//		.map(MatchResult::group)
			//		.distinct()
			//		.sorted()
			//		.forEach(System.out::println);

			var results = sc.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
					.skip(1)
					.map(m -> m.group(3).trim())
					.distinct()
					.sorted()
					.toArray(String[]::new);
			System.out.println(Arrays.toString(results));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try (Scanner sc = new Scanner(new BufferedReader(new FileReader("fixedWidth.txt")))) {
			//while (sc.hasNextLine()) {
			//	System.out.println(sc.nextLine());
			//}
			//System.out.println(sc.delimiter());
			//sc.useDelimiter("$");	// $ = meta character for end of line
			//sc.tokens().forEach(System.out::println); // tokens() method is used to get a stream of delimiter separated
			// tokens from the Scanner object
			//sc.findAll("[A-Za-z]{10,}")
			//		.map(MatchResult::group)
			//		.distinct()
			//		.sorted()
			//		.forEach(System.out::println);

			var results = sc.findAll("(.{15})(.{3})(.{12})(.{8})(.{2}).*")
					.skip(1)
					.map(m -> m.group(3).trim())
					.distinct()
					.sorted()
					.toArray(String[]::new);
			System.out.println(Arrays.toString(results));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
