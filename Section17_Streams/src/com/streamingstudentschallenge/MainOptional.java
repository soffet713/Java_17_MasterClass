package com.streamingstudentschallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {

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
                .collect(Collectors.toList());

        Optional<Student> o1 = getStudent(new ArrayList<>(), "first");
        System.out.println("Empty test = " + o1.isEmpty() + ", Present = " + o1.isPresent());
        System.out.println(o1);
        o1.ifPresentOrElse(System.out::println, () -> System.out.println("---> Empty Optional"));

        //students.add(0, null);
        Optional<Student> o2 = getStudent(students, "first");
        System.out.println("Empty test = " + o2.isEmpty() + ", Present = " + o2.isPresent());
        System.out.println(o2);
        o2.ifPresent(System.out::println);

        //Student firstStudent = o2.orElse(getDummyStudent(jmc,jn3,amc,fsmc));
        Student firstStudent = o2.orElseGet(() -> getDummyStudent(jmc,jn3,amc,fsmc));
        long id = firstStudent.getStudentId();
        System.out.println("firstStudent's id is " + id);
        System.out.println(firstStudent);

        List<String> countries = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .toList();

        Optional.of(countries)
                .map(l -> String.join(", ", l))
                .filter(l -> l.contains("FR"))
                .ifPresentOrElse(System.out::println, () -> System.out.println("Missing FR"));
    }

    private static Optional<Student> getStudent(List<Student> list, String type) {
        if(list==null || list.isEmpty()) {
            return Optional.empty();
        } else if(type.equalsIgnoreCase("first")) {
            return Optional.ofNullable(list.get(0));
        } else if(type.equalsIgnoreCase("last")) {
            return Optional.ofNullable(list.get(list.size()-1));
        }
        return Optional.of(list.get(new Random().nextInt(list.size())));
    }

    private static Student getDummyStudent(Course... courses) {
        System.out.println("Getting the dummy student");
        return new Student("NO",1,1,"U",false,courses);
    }
}
