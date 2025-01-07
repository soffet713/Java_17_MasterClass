package com.anonymousclasses;

import com.nestedclasses.domain.Employee;
import com.nestedclasses.domain.EmployeeComparator;
import com.nestedclasses.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RunMethods {

    public static void main(String[] args) {
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10002, "Meg", 2003, "Target"),
                new StoreEmployee(10004, "Jim", 2010, "Walmart"),
                new StoreEmployee(10014, "David", 2014, "Gamestop"),
                new StoreEmployee(10027, "Sarah", 2009, "Macys"),
                new StoreEmployee(10364, "Tom", 2011, "Target"),
                new StoreEmployee(10713, "Connor", 2017, "Gamestop")
        ));

        var c0 = new EmployeeComparator<StoreEmployee>();
        var c1 = new Employee.EmployeeComparator<StoreEmployee>();
        var c2 = new StoreEmployee().new StoreComparator<StoreEmployee>();

        class NameSort<T> implements Comparator<StoreEmployee> {
            @Override
            public int compare(StoreEmployee e1, StoreEmployee e2) {
                return e1.getName().compareTo(e2.getName());
            }
        }

        var c3 = new NameSort<StoreEmployee>();

        // Using Anonymous class
        var c4 = new Comparator<StoreEmployee>() {
            @Override
            public int compare(StoreEmployee e1, StoreEmployee e2) {
                return e1.getName().compareTo(e2.getName());
            }
        };

        sortIt(storeEmployees, c0);
        sortIt(storeEmployees, c1);
        sortIt(storeEmployees, c2);
        sortIt(storeEmployees, c3);
        sortIt(storeEmployees, c4);
        sortIt(storeEmployees, (e1, e2) -> e1.getName().compareTo(e2.getName()));
    }

    public static <T> void sortIt(List<T> list, Comparator<? super T> comparator) {

        System.out.println("Sorting with Comparator: " + comparator.toString());
        list.sort(comparator);
        for(var employee : list) {
            System.out.println(employee);
        }
    }
}
