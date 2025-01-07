package com.streamingstudentschallenge;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class MainMapping {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        Course jn3 = new Course("JN3", "Japanese N3 Class");
        Course amc = new Course("AMC", "Astronomy Masterclass");
        Course phmc = new Course("PHMC", "Physics Masterclass");
        Course fsmc = new Course("FSMC", "Film Studies Masterclass");
        Course acmc = new Course("ACMC", "Accounting Masterclass");
        Course wdmc = new Course("WDMC", "Web Design Masterclass");

        List<Student> students = IntStream.rangeClosed(1,5000)
                .mapToObj(s -> Student.getRandomStudent(pymc, jmc, jn3, amc, phmc, fsmc, acmc, wdmc))
                .toList();

        //map students by countrycode, using the country code as the key
        var mappedStudents = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));

        //print out each key(countryCode) and number of students mapped to it
        mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));

        System.out.println("-".repeat(75));
        int minAge = 25;

        //print country code and students under the age of 25 using filter, sorting, and collect
        var countryCodeUnder25 = students.stream()
                .filter(s -> s.getAge() <= minAge)
                .sorted(Comparator.comparing(Student::getCountryCode))
                .collect(Collectors.groupingBy(Student::getCountryCode));

        countryCodeUnder25.forEach((k, v) -> System.out.print(k + " " + v.size() + " | "));
        System.out.println();
        System.out.println("-".repeat(75));

        //course solution to print country codes and number of students under the age of 25 using collect filtering
        var youngerSet = students.stream()
                .sorted(Comparator.comparing(Student::getCountryCode))
                .collect(groupingBy(Student::getCountryCode, filtering(s -> s.getAge() <= minAge, toList())));

        youngerSet.forEach((k, v) -> System.out.print(k + " " + v.size() + " | "));
        System.out.println();

        //Using collect -> partitioningBy to print number of students with programming experience
        var experienced = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience));
        System.out.println("Experienced Students = " + experienced.get(true).size());

        //Using collect -> partitioningBy, counting() to get the count of students with programming experience
        var expCount = students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience, counting()));
        System.out.println("Experienced Students = " + expCount.get(true));

        //Using collect -> partitioningBy, counting() to get count of active students with programming experience
        var experiencedAndActive = students.stream()
                .collect(partitioningBy(s -> s.hasProgrammingExperience() && s.getMonthsSinceActive() == 0, counting()));
        System.out.println("Experienced and Active Students = " + experiencedAndActive.get(true));

        //using collect and nested grouping by to group students by country code and gender
        var multiLevel = students.stream()
                .collect(groupingBy(Student::getCountryCode, groupingBy(Student::getGender)));

        multiLevel.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((key1, value1) ->
                    System.out.printf("\t%s %d%n", key1, value1.size()));
        });

        long studentBodyCount = 0;
        //Different ways of getting the count of experienced students
        for(var list : experienced.values()) {
            studentBodyCount += list.size();
        }
        System.out.println("studentBodyCount = " + studentBodyCount);

        studentBodyCount = experienced.values().stream()
                .mapToInt(List::size) //l -> l.size()
                .sum();
        System.out.println("studentBodyCount = " + studentBodyCount);

        //long way of getting the count of students who have been active in the last 4 months
        studentBodyCount = experienced.values().stream()
                .map(l -> l.stream()
                        .filter(s -> s.getMonthsSinceActive() <= 3)
                        .count()
                )
                .mapToLong(l -> l)
                .sum();
        System.out.println("studentBodyCount active in last 4 months = " + studentBodyCount);

        long count = experienced.values().stream()
                .flatMap(Collection::stream) //l -> l.stream()
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active Students = " + count);

        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream().flatMap(Collection::stream)) //l -> l.stream()
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active Students = " + count);

        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(Collection::stream) //l -> l.stream()
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active Students = " + count);
    }
}
