package com.studentchallenge.model;

import com.studentchallenge.util.QueryList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int studentCount = 30;
        List<Student> students = new ArrayList<>();
        for(int i=0; i < studentCount; i++) {
            students.add(new Student());
        }

        Comparator<Student> studentSorter = new StudentCourseAndYearComparator();
        students.sort(studentSorter);
        printList(students);

        System.out.println("-".repeat(100));
        System.out.println();

        students.sort(Comparator.naturalOrder());
        printList(students);

        System.out.println("-".repeat(100));
        System.out.println();

        Comparator<LPAStudent> lpaStudentComparator = new LPAStudentComparator();
        List<LPAStudent> lpaStudents = new ArrayList<>();
        for(int i=0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }
        lpaStudents.sort(studentSorter);
        printList(lpaStudents);

        testList(new ArrayList<String>(List.of("John","Peter","Matthew","Paul","David","Mary","Sarah")));
        testList(new ArrayList<Integer>(List.of(15,65,48,3,85,184,77,23,91)));

        var queryList = new QueryList<>(lpaStudents);
        var matches = queryList
                .getMatches("PercentComplete","50")
                .getMatches("Course","Java");
        matches.sort(lpaStudentComparator);
        printList(matches);
    }

    public static void printList(List<? extends Student> students) {
        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

    public static void testList(List<?> list) {
        for (var element : list) {
            if(element instanceof String s) {
                System.out.println("String: " + s.toUpperCase());
            } else if (element instanceof Integer i) {
                System.out.println("Integer: " + i.floatValue());
            }
        }
    }
}

