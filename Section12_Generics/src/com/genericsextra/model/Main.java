package com.genericsextra.model;

import com.genericsextra.util.QueryItem;
import com.genericsextra.util.QueryList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

record Employee(String name) implements QueryItem {

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        return false;
    }
}

public class Main {

    public static void main(String[] args) {

        int studentCount = 10;
        List<Student> students = new ArrayList<>();
        for(int i=0; i < studentCount; i++) {
            students.add(new Student());
        }

        Comparator<Student> studentSorter = new StudentCourseAndYearComparator();
        students.sort(studentSorter);
        printList(students);

        List<LPAStudent> lpaStudents = new ArrayList<>();
        for(int i=0; i < studentCount; i++) {
            lpaStudents.add(new LPAStudent());
        }
        lpaStudents.sort(studentSorter);
        printList(lpaStudents);

        testList(new ArrayList<String>(List.of("John","Peter","Matthew","Paul","David","Mary","Sarah")));
        testList(new ArrayList<Integer>(List.of(15,65,48,3,85,184,77,23,91)));

        var queryList = new QueryList<>(lpaStudents);
        var matches = queryList.getMatches("Course","Java");
        printList(matches);

        var students2021 = QueryList.getMatches(students, "YearStarted", "2021");
        printList(students2021);

        //QueryList<Employee> employeeList = new QueryList<>();
    }

    public static void printList(List<? extends Student> students) {
        for (var student : students) {
            System.out.println(student.getYearStarted() + ": " + student);
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

//    public static void testList(List<String> list) {
//        for(var element : list) {
//            System.out.println("String: " + element.toUpperCase());
//        }
//    }
//
//    public static void testList(List<Integer> list) {
//        for(var element : list) {
//            System.out.println("Integer: " + element.floatValue());
//        }
//    }

//    public static <T extends Student> void printList(List<T> students) {
//
//        for (var student : students) {
//            System.out.println(student.getYearStarted() + ": " + student);
//        }
//        System.out.println();
//    }
}
