package com.immutableclasses.external;

import com.immutableclasses.PersonImmutable;
import com.immutableclasses.external.domain.LivingPerson;
import com.immutableclasses.external.hacker.PersonOfInterest;

public class MainImmutable {

    public static void main(String[] args) {

        PersonImmutable jane = new PersonImmutable("Jane", "02/21/2003");
        PersonImmutable jim = new PersonImmutable("Jim", "05/11/2005");
        PersonImmutable joe = new PersonImmutable("Joe", "10/03/2009");

        PersonImmutable[] johnsKids = {jane, jim, joe};
        PersonImmutable john = new PersonImmutable("John", "09/17/1977", johnsKids);

        System.out.println(john);

        PersonImmutable[] kids = john.getKids();
        kids[0] = jim;
        kids[1] = new PersonImmutable("Ann", "04/04/2014");
        System.out.println(john);

        johnsKids[0] = new PersonImmutable("Ann", "04/04/2014");
        System.out.println(john);

        LivingPerson johnLiving = new LivingPerson(john.getName(), john.getKids());
        System.out.println(johnLiving);

        LivingPerson anne = new LivingPerson("Anne",null);
        johnLiving.addKid(anne);
        System.out.println(johnLiving);

        PersonOfInterest johnCopy = new PersonOfInterest(john);
        System.out.println(johnCopy);

        kids = johnCopy.getKids();
        kids[1] = anne;
        System.out.println(johnCopy);
        System.out.println(john);
    }
}
