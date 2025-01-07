package com.comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Integer five = 5;
        Integer[] others = {0, 5, 10, -50, 50};

        for(Integer i : others) {
            int val = five.compareTo(i);
            System.out.printf("%d %s %d: compareTo = %d%n",five,(val == 0 ? "==" : (val < 0) ? "<" : ">"), i, val);
        }

        String banana = "banana";
        String[] fruit = {"apple","banana","orange","Pear","mango","BANANA"};

        for(String f : fruit) {
            int val = banana.compareTo(f);
            System.out.printf("%s %s %s: compareTo = %d%n",banana,(val == 0 ? "==" : (val < 0) ? "<" : ">"), f, val);
        }

        Arrays.sort(fruit);
        System.out.println(Arrays.toString(fruit));
        char[] letters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w',
        'x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X',
        'Y','Z'};

        for(char c : letters) {
            System.out.println(c + ": " + (int)c);
        }

        System.out.println();
        System.out.println("-".repeat(5));
        System.out.println();

        Arrays.sort(letters);
        for(char c : letters) {
            System.out.println(c + ": " + (int)c);
        }

        Student brian = new Student("Brian");
        Student[] students = {new Student("Zach"), new Student("Brian"), new Student("Ann"),
            new Student("Tim"), new Student("Jordan"), new Student("Andrew"),
                new Student("Jacob")};

        Arrays.sort(students);
        System.out.println(Arrays.toString(students));

        System.out.println("result = " + brian.compareTo(new Student("Mary")));

        Comparator<Student> gpaSorter = new StudentGPAComparator();
        Arrays.sort(students, gpaSorter.reversed());
        System.out.println(Arrays.toString(students));
    }
}

class StudentGPAComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        return (s1.gpa + s2.name).compareTo(s2.gpa + s2.name);
    }
}

class Student implements Comparable<Student> {

    private static int LAST_ID = 1000;
    private static Random random = new Random();

    protected String name;
    private int id;
    protected double gpa;

    public Student(String name) {
        this.name = name;
        id = LAST_ID++;
        gpa = random.nextDouble(2.0,4.5);
    }

    @Override
    public String toString() {
        return "%d - %s (%.2f)".formatted(id, name, gpa);
    }

    @Override
    public int compareTo(Student o) {
        return Integer.valueOf(id).compareTo(Integer.valueOf(o.id));
    }

    //@Override
    //public int compareTo(Object o) {
    //    Student other = (Student) o;
    //    return name.compareTo(other.name);
    //}
}
