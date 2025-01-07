package com.lambdaintro;

import java.util.*;

public class Main {

    record Person (String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {

        List<Person> myPeople = getPeopleList();

        //Using anonymous class
        var comparatorLastName = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName().compareTo(o2.lastName());
            }
        };

        myPeople.sort(comparatorLastName);
        for(Person p : myPeople) {
            System.out.println(p);
        }
        System.out.println();
        System.out.println("-".repeat(25));

        myPeople.sort((o1, o2) -> o1.firstName().compareTo(o2.firstName()));
        for(Person p : myPeople) {
            System.out.println(p);
        }
        System.out.println();
        System.out.println("-".repeat(25));

        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }

        var comparatorMixed = new EnhancedComparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName().compareTo(o2.lastName());
                return (result == 0 ? secondLevel(o1, o2) : result);
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName().compareTo(o2.firstName());
            }
        };

        myPeople.sort(comparatorMixed);

        for(Person p : myPeople) {
            System.out.println(p);
        }
        System.out.println();
        System.out.println("-".repeat(25));
    }

    public static List<Person> getPeopleList() {
        String[] firstNames = {"John","David","Matthew","Paul","Sarah","Emily","Brittney","Martin","Connor","Sam",
                "Michelle","Abigail","Jessica","Mark","Freddie","Laura","Alex","Scott","Megan","Natasha"};

        String[] lastNames = {"Jenkins","Smith","Johnson","Schmidt","Yamamoto","Jackson","Simpson","Sampson","Tully",
                "Benson","Christiansen","Garrison","Harrison","Williams","Thomas","Taylor","Moore","Kelly","Granger"};

        Random namePicker = new Random();

        List<Person> people = new ArrayList<>(Arrays.asList(
                new Main.Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)]),
                new Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)]),
                new Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)]),
                new Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)]),
                new Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)]),
                new Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)]),
                new Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)]),
                new Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)]),
                new Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)]),
                new Person(firstNames[namePicker.nextInt(firstNames.length)],
                        lastNames[namePicker.nextInt(lastNames.length)])
        ));

        return people;
    }
}
