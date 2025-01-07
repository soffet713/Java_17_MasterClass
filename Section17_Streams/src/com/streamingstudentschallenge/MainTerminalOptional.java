package com.streamingstudentschallenge;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class MainTerminalOptional {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        Course jn3 = new Course("JN3", "Japanese N3 Class");
        Course amc = new Course("AMC", "Astronomy Masterclass");
        Course phmc = new Course("PHMC", "Physics Masterclass");
        Course fsmc = new Course("FSMC", "Film Studies Masterclass");
        Course acmc = new Course("ACMC", "Accounting Masterclass");
        Course wdmc = new Course("WDMC", "Web Design Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc,jmc,jn3,amc,phmc,fsmc,acmc,wdmc))
                .limit(1000)
                .toList();

        int minAge = 17;
        //Get the students under or equal to the minAge, then get any one of them
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findAny()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(),
                        s.getCountryCode(), s.getAge()), () -> System.out.println("Didn't find anyone under " + minAge));

        //Get the students under or equal to the minAge, then get the first available student returned
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findFirst()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(),
                        s.getCountryCode(), s.getAge()), () -> System.out.println("Didn't find anyone under " + minAge));

        //Get the students under the minAge, then get the youngest student using the .min() method and a Comparator
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(),
                        s.getCountryCode(), s.getAge()), () -> System.out.println("Didn't find anyone under " + minAge));

        //Get the students under the minAge, then get the oldest student using the .max() method and a Comparator
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .max(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n", s.getStudentId(),
                        s.getCountryCode(), s.getAge()), () -> System.out.println("Didn't find anyone under " + minAge));

        //Get students under the min age and get the average age of those students
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .mapToInt(Student::getAge)
                .average()
                .ifPresentOrElse(a -> System.out.printf("Avg age under 21: %.2f%n", a),
                        () -> System.out.println("Didn't find anyone under " + minAge));


        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .map(Student::getCountryCode)
                .distinct()
                .reduce((a, b) -> String.join(",", a, b)) //reduce takes two parameters of the same type -
                .ifPresentOrElse(System.out::println,             //here it is two Strings, for the countries
                        () -> System.out.println("None found"));

        students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .map(l -> String.join(", ", l))
                .filter(l -> l.contains("AU"))
                .findAny()
                .ifPresentOrElse(System.out::println, () -> System.out.println("Missing AU"));
    }
}
