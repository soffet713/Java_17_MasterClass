package com.abstractclasses;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Dog buddy = new Dog("Retriever", "big", 75);
        //buddy.makeNoise();
        //doAnimalStuff(buddy,"fast");
        Dog lando = new Dog("Sheltie", "Medium", 35);
        //lando.makeNoise();
        //doAnimalStuff(lando,"sleeping");
        Dog balto = new Dog("Wolf", "big", 100);
        //balto.makeNoise();
        //doAnimalStuff(balto,"slow");

        ArrayList<Animal> animals = new ArrayList<>();

        Fish nemo = new Fish("Clownfish", "small", 2);
        Fish akira = new Fish("Betta Fish", "small", 1);
        Fish ares = new Fish("Barracuda", "big", 75);

        Cat garfield = new Cat("Orange Tabby Persian", "fat", 25);
        Cat aisu = new Cat("Tabby", "small",5);
        Cat kyra = new Cat("Calico", "medium", 10);

        animals.add(buddy);
        animals.add(nemo);
        animals.add(lando);
        animals.add(akira);
        animals.add(balto);
        animals.add(ares);
        animals.add(new Horse("Clydesdale", "large", 1350));
        animals.add(garfield);
        animals.add(aisu);
        animals.add(kyra);

        String[] speeds = new String[]{"fast","medium","slow","sleeping"};
        Random myRandom = new Random();

        for(Animal a : animals) {
            int index = myRandom.nextInt(speeds.length);
            doAnimalStuff(a,speeds[index]);
            if(a instanceof Mammal currentMammal) {
                currentMammal.shedHair();
            }
        }
    }

    private static void doAnimalStuff(Animal animal, String speed) {
        animal.makeNoise();
        animal.move(speed);
    }
}
