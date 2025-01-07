package com.readingtextchallenge;

import java.io.*;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

		Path path = Path.of("starWarsCrawls.txt");
		try (Scanner sc = new Scanner(new File(String.valueOf(path)))) {
			sc.useDelimiter("[.,!?:;'\"-]|\\s");
			//sc.tokens().forEach(System.out::println);

			System.out.println("-".repeat(25));

			sc.findAll("[A-Za-z]{6,}")
					.map(MatchResult::group)
					.distinct()
					.sorted()
					.forEach(System.out::println);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		System.out.println("-".repeat(45));

		try (BufferedReader br = new BufferedReader(new FileReader("starWarsCrawls.txt"))) {
			Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

			var result = br.lines()
					.flatMap(pattern::splitAsStream)
					.map(w -> w.replaceAll("\\p{Punct}", ""))
					.filter(w -> w.length() > 4)
					.map(String::toLowerCase)
					.collect(Collectors.groupingBy(w -> w, Collectors.counting()));

			result.entrySet().stream()
					.sorted(Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder()))
					.limit(10)
					.forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " times"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println("-".repeat(45));

		try (BufferedReader br = new BufferedReader(new FileReader("deadpoolwolverinereview.txt"))) {
			Pattern pattern = Pattern.compile("\\p{javaWhitespace}+");

			var result = br.lines()
					.flatMap(pattern::splitAsStream)
					.map(w -> w.replaceAll("\\p{Punct}", ""))
					.filter(w -> w.length() > 4)
					.map(String::toLowerCase)
					.collect(Collectors.groupingBy(w -> w, Collectors.counting()));

			result.entrySet().stream()
					.sorted(Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder()))
					.limit(10)
					.forEach(e -> System.out.println(e.getKey() + " - " + e.getValue() + " times"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
