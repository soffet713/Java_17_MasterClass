package com.sortedmaps;

import java.time.LocalDate;
import java.util.*;

public class Main {

    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();
    private static String separator = "-".repeat(75);
    private static Random random = new Random();

    public static void main(String[] args) {

        double[] prices = new double[]{109.99,119.99,129.99,139.99,149.99};
        Course jmc = new Course("jmc101", "Java Master Class", "Java");
        Course pmc = new Course("pmc101", "Python Master Class", "Python");
        Course jpmc = new Course("jpmc101", "Japanese Master Class", "Japanese");
        Course animc = new Course("ani101", "Animation Master Class", "Animation");
        Course fsmc = new Course("fsmc101", "Film Study Master Class", "Film");
        Course phmc = new Course("phmc101", "Physics Master Class", "Physics");
        Course calmc = new Course("calmc101", "Calculus Master Class", "Calculus");
        Course csmc = new Course("csmc101", "Computer Science Master Class", "Computer Science");
        Course cmc = new Course("cmc101", "C Master Class", "C");
        Course wdmc = new Course("wdmc101", "Web Design Master Class", "Web Design");
        Course astrmc = new Course("astrmc101", "Astronomy Master Class", "Astronomy");
        Course[] courses = new Course[]{jmc,pmc,jpmc,animc,fsmc,phmc,calmc,csmc,cmc,wdmc,astrmc};
        List<String> studentNames = getStudentNames(10);

        for(int s=0; s<studentNames.size(); s++) {
            for(int c=0; c<courses.length/2; c++) {
                addPurchase(studentNames.get(s),getRandomCourse(studentNames.get(s),courses),
                        prices[random.nextInt(prices.length-1)]);
            }
        }

        purchases.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println(separator);
        students.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println(separator);

        NavigableMap<LocalDate,List<Purchase>> datedPurchases = new TreeMap<>();

        for(Purchase p : purchases.values()) {
            datedPurchases.compute(p.purchaseDate(),
                    (pdate, plist) -> {
                        List<Purchase> list = (plist==null) ? new ArrayList<>() : plist;
                        list.add(p);
                        return list;
                    });
        }
        datedPurchases.forEach((key, value) -> System.out.println(key + ": " + value));

        int currentYear = LocalDate.now().getYear();

        LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
        LocalDate week1 = firstDay.plusDays(7);
        Map<LocalDate, List<Purchase>> week1Purchases = datedPurchases.headMap(week1);
        Map<LocalDate, List<Purchase>> week2Purchases = datedPurchases.tailMap(week1);

        //System.out.println(separator);
        //week1Purchases.forEach((key,value) -> System.out.println(key + ": " + value));
        //System.out.println(separator);
        //week2Purchases.forEach((key,value) -> System.out.println(key + ": " + value));

        displayStats(1, week1Purchases);
        displayStats(2, week2Purchases);

        System.out.println(separator);

        LocalDate lastDate = datedPurchases.lastKey();
        var previousEntry = datedPurchases.lastEntry();

        while(previousEntry!=null) {
            List<Purchase> lastDaysData = previousEntry.getValue();
            System.out.println(lastDate + " purchases : " + lastDaysData.size());

            LocalDate prevDate = datedPurchases.lowerKey(lastDate);
            previousEntry = datedPurchases.lowerEntry(lastDate);
            lastDate = prevDate;
        }

        System.out.println(separator);
        var reversed = datedPurchases.descendingMap();

        LocalDate firstDate = reversed.firstKey();
        //var nextEntry = reversed.firstEntry();
        var nextEntry = reversed.pollFirstEntry();

        while(nextEntry!=null) {
            List<Purchase> lastDaysData = nextEntry.getValue();
            System.out.println(firstDate + " purchases : " + lastDaysData.size());

            LocalDate nextDate = reversed.higherKey(firstDate);
            //nextEntry = reversed.higherEntry(firstDate);
            nextEntry = reversed.pollFirstEntry();
            firstDate = nextDate;
        }

        System.out.println(separator);
        datedPurchases.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    private static List<String> getStudentNames(int numStudents) {
        List<String> firstNames = new ArrayList<>(List.of("John","David","Matthew","Paul","Sarah","Emily",
                "Brittney","Martin","Connor","Michelle","Abigail","Jessica","Mark","Freddie","Laura","Alex","Brandon",
                "Chris","Henry","Kristin"));

        List<String> lastNames = new ArrayList<>(List.of("Jenkins","Smith","Johnson","Schmidt","Yamamoto",
                "Jackson","Simpson","Sampson","Benson","Cooper","Garrison","Harrison","Williams","Thomas","Taylor",
                "Moore","Kelly","Jones"));

        List<String> students = new ArrayList<>();
        while(students.size()<numStudents) {
            String currFirstName = firstNames.get(random.nextInt(firstNames.size()));
            String currLastName = lastNames.get(random.nextInt(lastNames.size()));
            String nextStudent = currFirstName + " " + currLastName;
            if(students.isEmpty()||!students.contains(nextStudent)) {
                students.add(nextStudent);
                firstNames.remove(currFirstName);
                lastNames.remove(currLastName);
            }
        }
        return students;
    }

    private static Course getRandomCourse(String name, Course[] courses) {
        Student existingStudent = students.get(name);
        Course randomCourse = null;
        if(existingStudent==null) {
            randomCourse = courses[random.nextInt(courses.length)];
        } else {
            while (randomCourse == null) {
                int randomSelected = random.nextInt(courses.length);
                randomCourse = existingStudent.getCourseList().contains(courses[randomSelected]) ?
                        null : courses[randomSelected];
            }
        }
        return randomCourse;
    }

    private static void addPurchase(String name, Course course, double price) {
        Student existingStudent = students.get(name);
        if(existingStudent==null) {
            existingStudent = new Student(name, course);
            students.put(name, existingStudent);
        } else {
            existingStudent.addCourse(course);
        }

        int day = random.nextInt(1,15);
        String key = course.courseId() + "_" + existingStudent.getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price, year, day);
        purchases.put(key, purchase);
    }

    public static void displayStats(int period, Map<LocalDate, List<Purchase>> periodData) {
        System.out.println(separator);
        Map<String, Integer> weeklyCounts = new TreeMap<>();
        periodData.forEach((key,value) -> {
            System.out.println(key + ": " + value);
            for(Purchase p : value) {
                weeklyCounts.merge(p.courseId(), 1, (prev, current) -> { return prev + current; });
            }
        });
        System.out.printf("Week %d Purchases = %s%n", period, weeklyCounts);
    }
}
