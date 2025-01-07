package com.abstractclasses;

public class Horse extends Mammal {

    public Horse(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void makeNoise() {
        System.out.print("Neigh~~~ ");
    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + " sheds in the Spring & Summer.");
    }

    @Override
    public void move(String speed) {
        System.out.print(getExplicitType() + " ");
        System.out.println(speed.equals("slow") ? "trots." : "gallops.");
    }
}
