package com.parallelstreamsandmore;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Person (String firstName, String lastName, int age) {

	private final static String[] firsts = {"John", "Anna", "Charlie", "Eve", "Donna", "Fred", "Michael","Sarah"};
	private final static String[] lasts = {"Norton", "Ohara", "Peterson", "Quincy", "Smith", "Johnson", "Jones",
											"Richardson"};
	private final static Random rand = new Random();

	public Person() {
		this(firsts[rand.nextInt(firsts.length)],
				lasts[rand.nextInt(lasts.length)],
				rand.nextInt(18,100));
	}

	@Override
	public String toString() {
		return "%s, %s (%d)".formatted(lastName, firstName, age);
	}
}

public class Main {

	public static void main(String[] args) {

		var persons = Stream.generate(Person::new)
						.limit(10)
						.sorted(Comparator.comparing(Person::lastName))
						.toArray();

		for (var person : persons) {
			System.out.println(person);
		}

		System.out.println("-".repeat(45));

		//Stream.generate(Person::new)
		Arrays.stream(persons)
				.limit(10)
				.parallel()
				//.sorted(Comparator.comparing(Person::lastName))
				.forEach(System.out::println);

		System.out.println("-".repeat(45));

		int sum = IntStream.range(1, 101)
				.parallel()
				.reduce(0, Integer::sum);

		System.out.println("The sum of the numbers is: " + sum);

		String humptyDumpty = """
				Humpty Dumpty sat on a wall.
				Humpty Dumpty had a great fall.
				All the king's horses and all the king's men,
				couldn't put Humpty together again.
				""";

		System.out.println("-".repeat(45));
		var words = new Scanner(humptyDumpty).tokens().toList();
		words.forEach(System.out::println);
		System.out.println("-".repeat(45));

		//var backTogetherAgain = words.stream().reduce(new StringJoiner(" "),
		//		StringJoiner::add,
		//		StringJoiner::merge);

		//var backTogetherAgain = words
		//		.parallelStream()
		//		.collect(Collectors.joining(" "));

		var backTogetherAgain = words
				.parallelStream()
				//.reduce("", (s1, s2) -> s1.concat(s2).concat(" "));
				.collect(Collectors.joining(" "));

		//String togetherAgain = backTogetherAgain.toString()
		//		.replaceAll("[.] ", ".\n").replaceAll("[,] ", ",\n");

		String togetherAgain = backTogetherAgain
				.replaceAll("[.] ", ".\n").replaceAll("[,] ", ",\n");

		System.out.println(backTogetherAgain);
		System.out.println(togetherAgain);

		System.out.println("-".repeat(45));

		// This code for maps is not efficient, so better code is written below
		//Map<String, Long> lastNameCounts = Stream.generate(Person::new)
		//		.limit(10000)
		//		.parallel()
		//		.collect(Collectors.groupingBy(
		//				Person::lastName,
		//				Collectors.counting()
		//		));

		Map<String, Long> lastNameCounts = Stream.generate(Person::new)
				.limit(10000)
				.parallel()
				.collect(Collectors.groupingByConcurrent(
						Person::lastName,
						Collectors.counting()
				));

		lastNameCounts.entrySet().forEach(System.out::println);

		long total = 0;
		for (long count : lastNameCounts.values()) {
			total += count;
		}
		System.out.println("Total = " + total);

		System.out.println(lastNameCounts.getClass().getName());

		/************* DO NOT DO THIS *************/
		/*
		var lastCounts = new TreeMap<String, Long>();
		Stream.generate(Person::new)
				.limit(10000)
				.parallel()
				.forEach((person) -> lastCounts.merge(person.lastName(), 1L, Long::sum));

		System.out.println(lastCounts);

		total = 0;
		for (long count : lastCounts.values()) {
			total += count;
		}
		System.out.println("Total = " + total);

		System.out.println(lastCounts.getClass().getName());
		 */
		/************* *****************************/

		var concurrentCounts = new ConcurrentSkipListMap<String, Long>();
		Stream.generate(Person::new)
				.limit(10000)
				.parallel()
				.forEach((person) -> concurrentCounts.merge(person.lastName(), 1L, Long::sum));

		System.out.println(concurrentCounts);

		total = 0;
		for (long count : concurrentCounts.values()) {
			total += count;
		}
		System.out.println("Total = " + total);

		System.out.println(concurrentCounts.getClass().getName());

		var synchronizedCounts = Collections.synchronizedMap(new TreeMap<String, Long>());
		Stream.generate(Person::new)
				.limit(10000)
				.parallel()
				.forEach((person) -> synchronizedCounts.merge(person.lastName(), 1L, Long::sum));

		System.out.println(synchronizedCounts);

		total = 0;
		for (long count : synchronizedCounts.values()) {
			total += count;
		}
		System.out.println("Total = " + total);

		System.out.println(synchronizedCounts.getClass().getName());
	}
}
