package com.challengesolution;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String[] firstNames = {"John","David","Matthew","Paul","Sarah","Emily","Brittney","Martin","Connor",
                "Michelle","Abigail","Jessica","Mark","Freddie","Laura"};

        String[] lastNames = {"Jenkins","Smith","Johnson","Schmidt","Yamamoto","Jackson","Simpson","Sampson",
                "Benson","Christiansen","Garrison","Harrison","Williams","Thomas","Taylor","Moore","Kelly"};

        Random namePicker = new Random();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Employee> list = new ArrayList<>(List.of(
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf)),
                new Employee(firstNames[namePicker.nextInt(15)],lastNames[namePicker.nextInt(17)],
                        LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 15)))).format(dtf))
        ));

        printOrderedList(list, "name");
        System.out.println();
        System.out.println("-".repeat(35));
        printOrderedList(list, "year");
    }

    public static void printOrderedList(List<Employee> eList, String sortField) {

        int currentYear = LocalDate.now().getYear();

        class MyEmployee {

            Employee currentEmployee;
            int yearsWorked;
            String fullName;

            public MyEmployee(Employee currentEmployee) {
                this.currentEmployee = currentEmployee;
                yearsWorked = currentYear - Integer.parseInt(currentEmployee.hireDate().split("-")[0]);
                fullName = String.join(" ",currentEmployee.first(),currentEmployee.last());
            }

            @Override
            public String toString() {
                return "%s has been an employee for %d years. They started on %s".formatted(fullName,yearsWorked,
                        currentEmployee.hireDate());
            }
        }

        List<MyEmployee> myEmployeeList = new ArrayList<>();
        for (Employee e : eList) {
            myEmployeeList.add(new MyEmployee(e));
        }

        var comparator = new Comparator<MyEmployee>() {
            @Override
            public int compare(MyEmployee o1, MyEmployee o2) {
                if(sortField.equals("name")) {
                    return o1.fullName.compareTo(o2.fullName);
                }
                return o1.yearsWorked - o2.yearsWorked;
            }
        };

        myEmployeeList.sort(comparator);

        for(MyEmployee myemp : myEmployeeList) {
            System.out.println(myemp);
        }
    }
}
