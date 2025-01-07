package com.immutableclasses.external.hacker;

import com.immutableclasses.PersonImmutable;

public class PersonOfInterest extends PersonImmutable {

    public PersonOfInterest(PersonImmutable person) {
        super(person);
    }
}
