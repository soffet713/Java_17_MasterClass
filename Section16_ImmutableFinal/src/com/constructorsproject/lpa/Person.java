package com.constructorsproject.lpa;

public record Person(String name, String dob) {

    //public Person(String name, String dob) {
    //    this.name = name;
    //    this.dob = dob.replace('-', '/').trim();
    //}

    public Person(Person p) {
        this(p.name, p.dob);
    }

    public Person {
        if(dob==null) throw new IllegalArgumentException("Bad dob data");
        dob = dob.replace('-','/');
    }
}
