package com.streamingstudentschallenge;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class MainChallenge {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jn3 = new Course("JN3", "Japanese N3 Class");
        Course amc = new Course("AMC", "Astronomy Masterclass");
        Course phmc = new Course("PHMC", "Physics Masterclass");
        Course fsmc = new Course("FSMC", "Film Studies Masterclass");
        Course acmc = new Course("ACMC", "Accounting Masterclass");
        Course wdmc = new Course("WDMC", "Web Design Masterclass");
        Course cgj = new Course("CGJ", "Creating Games in Java");
        Course cgp = new Course("CGP", "Creating Games in Python");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(pymc,jmc,jn3,amc,
                phmc,fsmc,acmc,wdmc))
                .limit(5000)
                .toList();

        double jmcTotalPercent = students.stream()
                .filter((s) -> s.isEnrolled(jmc))
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .reduce(0, Double::sum);

        double averagePercent = jmcTotalPercent / students.stream().filter((s) -> s.isEnrolled(jmc)).count();

        System.out.printf("Average percent complete of students taking Java Masterclass: %.2f%%%n", averagePercent);

        int topPercent = (int) (1.25 * averagePercent);
        System.out.printf("Best percentage complete: %d%% %n", topPercent);

        Comparator<Student> longTermStudent = Comparator.comparing(Student::getYearEnrolled);

        List<Student> bestStudents = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(longTermStudent)
                .limit(10)
                .toList();

        bestStudents.forEach(s -> {
            s.addCourse(cgj);
            System.out.print(s.getStudentId() + " ");
        });
        System.out.println();

        Comparator<Student> uniqueSorted = longTermStudent.thenComparing((Student::getStudentId));

        double pymcTotalPercent = students.stream()
                .filter((s) -> s.isEnrolled(pymc))
                .mapToDouble(s -> s.getPercentComplete("PYMC"))
                .reduce(0, Double::sum);

        double averagePYMCPercent = jmcTotalPercent / students.stream().filter((s) -> s.isEnrolled(pymc)).count();

        int topPYMCPercent = (int) (1.25 * averagePYMCPercent);

        students.stream().filter((s) -> s.isEnrolled(pymc))
                .filter(s -> s.getMonthsSinceActive("PYMC") == 0)
                .filter(s -> s.getPercentComplete("PYMC") >= topPYMCPercent)
                .sorted(longTermStudent)
                .limit(10)
                .forEach(s -> {
                    s.addCourse(cgp);
                    System.out.print(s.getStudentId() + " ");
                });
    }
}
