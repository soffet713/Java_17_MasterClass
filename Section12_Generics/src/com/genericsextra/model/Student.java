package com.genericsextra.model;

import com.genericsextra.util.QueryItem;

import java.util.Comparator;
import java.util.Random;

public class Student implements Comparable<Student>, QueryItem {

    private String name;
    private String course;
    private int yearStarted;

    protected static Random random = new Random();

    private static String[] firstNames = {"Mary","Sarah","Emily","Megan","Rachel","John","David","Matthew",
            "Paul","Josiah","Peter"};

    private static String[] courses = {"C#","C++","Java","Python","Astronomy","Calculus","Accounting","Animation",
            "Japanese","Physics"};

    public Student() {
        int lastNameIndex = random.nextInt(65,91);
        name = firstNames[random.nextInt(11)] + " " + (char)lastNameIndex;
        course = courses[random.nextInt(10)];
        yearStarted = random.nextInt(2018,2023);
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public int compareTo(Student s) {
        return Integer.valueOf(name).compareTo(Integer.valueOf(s.name));
    }

    @Override
    public String toString() {
        return "%-15s | %-15s | %d".formatted(name, course, yearStarted);
    }


    @Override
    public boolean matchFieldValue(String fieldName, String value) {

        String fName = fieldName.toUpperCase();
        return switch(fName) {
            case "NAME" -> name.equalsIgnoreCase(value);
            case "COURSE" -> course.equalsIgnoreCase(value);
            case "YEARSTARTED" -> yearStarted == (Integer.parseInt(value));
            default -> false;
        };
    }
}

class StudentCourseComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return (s1.getCourse() + s1.getName()).compareTo(s2.getCourse() + s2.getName());
    }
}

class StudentCourseAndYearComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return (s1.getCourse() + s1.getYearStarted() + s1.getName()).compareTo(
                s2.getCourse() + s2.getYearStarted() + s2.getName());
    }
}
