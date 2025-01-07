package com.unmodifiablecollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    static String separator = "-".repeat(75);

    public static void main(String[] args) {

        StringBuilder bobsNotes = new StringBuilder();
        StringBuilder billsNotes = new StringBuilder("Bill struggles with generics");

        Student bob = new Student("Bob", bobsNotes);
        Student bill = new Student("Bill", billsNotes);

        List<Student> students = new ArrayList<>(List.of(bob, bill));
        List<Student> studentsFirstCopy = new ArrayList<>(students);
        List<Student> studentsSecondCopy = List.copyOf(students);
        List<Student> studentsThirdCopy = Collections.unmodifiableList(students); //actually returns an unmodifiable view

        studentsFirstCopy.add(new Student("Bonnie", new StringBuilder()));
        //studentsSecondCopy.add(new Student("Bonnie", new StringBuilder()));
        studentsFirstCopy.sort(Comparator.comparing(Student::getName));
        students.add(new Student("Bonnie", new StringBuilder()));
        bobsNotes.append("Bob was one of the first students.");

        StringBuilder bonniesNotes = studentsFirstCopy.get(2).getStudentNotes();
        bonniesNotes.append("Bonnie is taking 3 courses.");

        students.forEach(System.out::println);
        System.out.println(separator);
        studentsFirstCopy.forEach(System.out::println);
        System.out.println(separator);
        studentsSecondCopy.forEach(System.out::println);
        System.out.println(separator);
        studentsThirdCopy.forEach(System.out::println);
        System.out.println(separator);
    }
}
