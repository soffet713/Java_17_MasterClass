package com.streamingstudentschallenge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        Course jn3 = new Course("JN3", "Japanese N3 Class");
        Course amc = new Course("AMC", "Astronomy Masterclass");
        Course phmc = new Course("PHMC", "Physics Masterclass");
        Course fsmc = new Course("FSMC", "Film Studies Masterclass");
        Course acmc = new Course("ACMC", "Accounting Masterclass");
        Course wdmc = new Course("WDMC", "Web Design Masterclass");
/*
        Student dan = new Student("JP", 2021, 30, "M", true,
                pymc,jmc,jn3,amc);

        System.out.println(dan);

        dan.watchLecture("JMC", 10, 3, 2021);
        dan.watchLecture("PYMC", 7, 4, 2021);
        System.out.println(dan);
 */

        Student[] randomStudents = new Student[1000];
        Arrays.setAll(randomStudents, (i) -> Student.getRandomStudent(pymc,jmc,jn3,amc,phmc,fsmc,acmc,wdmc));

        for(String gender : List.of("M","F","U")) {
            var studentStream = Arrays.stream(randomStudents)
                    .filter(s -> s.getGender().equals(gender));
            String currGender = (gender.equals("M")) ? "male" : ((gender.equals("F")) ? "female" : "undisclosed");
            System.out.println("Number of " + currGender + " students: " + studentStream.count());
        }

        List<Predicate<Student>> studentList = List.of(
                (s) -> s.getAge() < 30,
                (Student s) -> s.getAge() >= 30 && s.getAge() < 60
        );

        long totalStudents = 0;
        for(int i = 0; i < studentList.size(); i++) {
            var studentAgeStream = Arrays.stream(randomStudents).filter(studentList.get(i));
            long studentCount = studentAgeStream.count();
            totalStudents += studentCount;
            System.out.printf("Number of students (%s) = %d%n", i == 0 ? "< 30" : ">= 30 & < 60", studentCount);
        }
        System.out.println("Number of students >= 60 = " + (randomStudents.length - totalStudents));

        var ageStream = Arrays.stream(randomStudents)
                .mapToInt(Student::getAgeEnrolled);
        System.out.println("Stats for enrollment age: " + ageStream.summaryStatistics());

        var currentAgeStream = Arrays.stream(randomStudents)
                .mapToInt(Student::getAge);
        System.out.println("Stats for current age: " + currentAgeStream.summaryStatistics());

        Arrays.stream(randomStudents)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        boolean longTerm = Arrays.stream(randomStudents)
                .anyMatch(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() <12));

        System.out.println("Present long term students = " + longTerm);

        long longTermCount = Arrays.stream(randomStudents)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() <12))
                .count();

        System.out.println("Number of long term students: " + longTermCount);
        System.out.println("-".repeat(75));

        List<Student> longTimeLearnerList = Arrays.stream(randomStudents)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() <12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .toList();

        longTimeLearnerList.forEach(System.out::println);
        System.out.println("-".repeat(75));

        var longTimeLearnerArray = Arrays.stream(randomStudents)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() <12))
                .filter(Student::hasProgrammingExperience)
                .limit(5)
                .toArray(Student[]::new);
        for(Student s : longTimeLearnerArray) {
            System.out.println(s);
        }
        System.out.println("-".repeat(75));

        var learners = Arrays.stream(randomStudents)
                .filter(s -> (s.getAge() - s.getAgeEnrolled() >= 7) && (s.getMonthsSinceActive() <12))
                .filter(s -> !s.hasProgrammingExperience())
                .limit(5)
                .collect(Collectors.toList()); //collect terminal operation returns mutable reductions

        Collections.shuffle(learners);
        learners.forEach(System.out::println);
        System.out.println("-".repeat(75));
    }
}
