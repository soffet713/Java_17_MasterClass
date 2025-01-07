package com.streamingstudentschallenge;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainFinalChallenge {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass", 50);
        Course jn3 = new Course("JN3", "Japanese N3 Class", 50);
        Course amc = new Course("AMC", "Astronomy Masterclass");
        Course phmc = new Course("PHMC", "Physics Masterclass");
        Course fsmc = new Course("FSMC", "Film Studies Masterclass");
        Course acmc = new Course("ACMC", "Accounting Masterclass");
        Course wdmc = new Course("WDMC", "Web Design Masterclass");
        Course cgj = new Course("CGJ", "Creating Games in Java", 50);
        Course cgp = new Course("CGP", "Creating Games in Python");

        int currentYear = LocalDate.now().getYear();
        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc,jmc,jn3,amc,
                        phmc,fsmc,acmc,wdmc,cgj,cgp))
                .filter(s -> s.getYearEnrolled() >= (currentYear - 4))
                .limit(10000)
                .toList();

        System.out.println(students.stream().mapToInt(Student::getYearEnrolled).summaryStatistics());
        System.out.println("-".repeat(75));

        students.subList(0, 10).forEach(System.out::println);
        System.out.println("-".repeat(75));

        System.out.println(students.stream()
                .mapToInt(s ->s.getEngagementMap().size())
                .summaryStatistics());
        System.out.println("-".repeat(75));

        var mappedActivity = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream()) //creating a stream with the engagementMap
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode, //groupingBy the CourseCode
                        Collectors.counting()));                                //and doing a count

        mappedActivity.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("-".repeat(75));

        var classCounts = students.stream()
                .collect(Collectors.groupingBy(s -> s.getEngagementMap().size(),
                        Collectors.counting()));

        classCounts.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("-".repeat(75));

        var percentages = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.summarizingDouble(CourseEngagement::getPercentComplete)));

        percentages.forEach((k,v) -> System.out.println(k + " " + v));
        System.out.println("-".repeat(75));

        var yearMap = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.groupingBy(CourseEngagement::getLastActivityYear, Collectors.counting())));

        yearMap.forEach((k, v) -> System.out.println(k + " " + v));
        System.out.println("-".repeat(75));

        students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getEnrollmentYear,
                        Collectors.groupingBy(CourseEngagement::getCourseCode,
                                Collectors.counting())))
                .forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("-".repeat(75));
    }
}
