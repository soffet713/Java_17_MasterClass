package com.immutableclasses;

public class MainRecord {

    public static void main(String[] args) {

        PersonRecord jane = new PersonRecord("Jane", "02/21/2003");
        PersonRecord jim = new PersonRecord("Jim", "05/11/2005");
        PersonRecord joe = new PersonRecord("Joe", "10/03/2009");

        PersonRecord[] johnsKids = {jane, jim, joe};
        PersonRecord john = new PersonRecord("John", "09/17/1977", johnsKids);

        System.out.println(john);

        PersonRecord johnCopy = new PersonRecord("John", "09/17/1977");
        System.out.println(johnCopy);

        PersonRecord[] kids = johnCopy.kids();
        kids[0] = jim;
        kids[1] = new PersonRecord("Ann", "04/04/2014");
        System.out.println(johnCopy);

        johnsKids[0] = new PersonRecord("Ann", "04/04/2014");
        System.out.println(john);
    }
}
