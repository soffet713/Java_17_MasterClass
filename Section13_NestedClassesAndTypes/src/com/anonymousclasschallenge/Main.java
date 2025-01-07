package com.anonymousclasschallenge;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

record Employee(String firstName, String lastName, String hireDate) {
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getHireDate() { return hireDate; }

    @Override
    public String toString() {
        return "First Name: " + firstName + ", Last Name: " + lastName + " : Hire Date: " + hireDate;
    }
}

public class Main {

    public static void main(String[] args) {

        String[] firstNames = {"John","David","Matthew","Paul","Sarah","Emily","Brittney","Martin","Connor",
                "Michelle","Abigail","Jessica","Mark","Freddie","Laura"};

        String[] lastNames = {"Jenkins","Smith","Johnson","Schmidt","Yamamoto","Jackson","Simpson","Sampson",
                "Benson","Christiansen","Garrison","Harrison","Williams","Thomas","Taylor","Moore","Kelly"};

        Random namePicker = new Random();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Employee> employees = new ArrayList<>(List.of(
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

        class NameSort<T> implements Comparator<Employee> {
            @Override
            public int compare(Employee o1, Employee o2) {
                int diff = o1.getLastName().compareTo(o2.getLastName());
                if(diff == 0) {
                    diff = o1.getFirstName().compareTo(o2.getFirstName());
                }
                return diff;
            }
        }

        var comp = new NameSort<Employee>();

        employees.sort(comp);

        class EmployeeWrapper implements Comparator<Employee> {
            Employee myEmployee;
            String fullName;
            double yearsWorked;

            public EmployeeWrapper(Employee myEmployee) {
                this.myEmployee = myEmployee;
                this.fullName = myEmployee.getFirstName() + " " + myEmployee.getLastName();
                this.yearsWorked = getYearsWorked();
            }

            private double getYearsWorked() {
                LocalDate newDate = LocalDate.parse(myEmployee.getHireDate());
                LocalDate currDate = LocalDate.now();
                Duration diff = Duration.between(newDate.atStartOfDay(),currDate.atStartOfDay());
                double diffDays = diff.toDays();
                return diffDays/365;
            }

            @Override
            public String toString() {
                return "%s has worked at the company for %.2f years. They started on: %s.".formatted(fullName,
                        yearsWorked,myEmployee.getHireDate());
            }

            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        }

        List<EmployeeWrapper> employeeWrappers = new ArrayList<>(employees.size());

        for(Employee e : employees) {
            System.out.println(e);
            employeeWrappers.add(new EmployeeWrapper(e));
        }

        System.out.println();
        System.out.println("-".repeat(150));

        for(EmployeeWrapper ew : employeeWrappers) {
            System.out.println(ew);
        }

        var comp1 = new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                int yearDiff = LocalDate.parse(e1.getHireDate()).getYear() - LocalDate.parse(e2.getHireDate()).getYear();
                if(yearDiff==0) {
                    int monthDiff = LocalDate.parse(e1.getHireDate()).getMonthValue() -
                            LocalDate.parse(e2.getHireDate()).getMonthValue();
                    if(monthDiff==0) {
                        int dayDiff = LocalDate.parse(e1.getHireDate()).getDayOfMonth() -
                                LocalDate.parse(e2.getHireDate()).getDayOfMonth();
                        if(dayDiff==0) {
                            int diff = e1.getLastName().compareTo(e2.getLastName());
                            if(diff == 0) {
                                diff = e1.getFirstName().compareTo(e2.getFirstName());
                            }
                            return diff;
                        } else {
                            return dayDiff;
                        }
                    } else {
                        return monthDiff;
                    }
                } else {
                    return yearDiff;
                }
            }
        };

        employees.sort(comp1);

        System.out.println();
        System.out.println("-".repeat(150));

        for(Employee e : employees) {
            System.out.println(e);
        }

        var comp2 = new Comparator<EmployeeWrapper>() {
            @Override
            public int compare(EmployeeWrapper e1, EmployeeWrapper e2) {
                return (int)e1.getYearsWorked() - (int)e2.getYearsWorked();
            }
        };

        employeeWrappers.sort(comp2);

        System.out.println();
        System.out.println("-".repeat(150));

        for(EmployeeWrapper ew : employeeWrappers) {
            System.out.println(ew);
        }
    }
}
