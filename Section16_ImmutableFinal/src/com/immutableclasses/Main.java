package com.immutableclasses;

public class Main {

    public static void main(String[] args) {
        //Code from start of section video
        //Person jane = new Person();
        //jane.setName("Jane");
        //Person jim = new Person();
        //jim.setName("Jim");
        //Person joe = new Person();
        //joe.setName("Joe");
        //Person john = new Person();
        //john.setName("John");
        //john.setDob("09/05/1977");
        //john.setKids(new Person[]{jane,jim,joe});
        //System.out.println(john);

        //john.setName("Jacob");
        //john.setKids(new Person[]{new Person(), new Person()});
        //System.out.println(john);

        Person jane = new Person("Jane", "02/21/2003");
        Person jim = new Person("Jim", "05/11/2005");
        Person joe = new Person("Joe", "10/03/2009");

        Person[] johnsKids = {jane, jim, joe};
        Person john = new Person("John", "09/17/1977", johnsKids);

        System.out.println(john);

        john.setKids(new Person[]{new Person("Ann", "04/04/2014")});
        System.out.println(john);

        Person[] kids = john.getKids();
        kids[0] = jim;
        System.out.println(john);

        kids = null;
        System.out.println(john);

        john.setKids(kids);
        System.out.println(john);
    }
}
