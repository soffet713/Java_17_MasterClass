package com.immutableclasses;

import java.util.Arrays;

public class Person {

    private String name;
    private String dob;
    private Person[] kids;

    public Person(String name, String dob) {
        this(name, dob, null);
    }

    public Person(String name, String dob, Person[] kids) {
        this.name = name;
        this.dob = dob;
        this.kids = kids;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public Person[] getKids() {
        return kids;
    }

    protected void setKids(Person[] kids) {
        if(kids==null) { System.out.println("kids is null."); }
        else if(this.kids.length>0) {
            Person[] currKids = this.kids;
            int numKids = kids.length + this.kids.length;
            Person[] allKids = new Person[numKids];
            for(int i=0; i<this.kids.length; i++) {
                allKids[i] = this.kids[i];
            }
            for(int x=0; x<kids.length; x++) {
                allKids[x+this.kids.length] = kids[x];
            }
            this.kids = allKids;
        } else {
            this.kids = kids;
        }
    }

    @Override
    public String toString() {
        String kidString = "n/a";
        if(kids != null) {
            String[] names = new String[kids.length];
            Arrays.setAll(names, i -> names[i] = kids[i] == null ? "" : kids[i].name);
            kidString = String.join(", ", names);
        }
        return name + ", dob = " + dob + ", kids = " + kidString;
    }
}
