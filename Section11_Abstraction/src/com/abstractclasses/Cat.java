package com.abstractclasses;

public class Cat extends Mammal {

    public Cat(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void makeNoise() {
        System.out.print("Meow ");
    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + " sheds more when the temperature is warm");
    }

    @Override
    public void move(String speed) {
        switch(speed) {
            case "slow" : System.out.println(getExplicitType() + " silently creeping around.");
                break;
            case "fast" : System.out.println(getExplicitType() + " darting across the room.");
                break;
            case "medium" : System.out.println(getExplicitType() + " elegantly sauntering without a care.");
                break;
            default : System.out.println(getExplicitType() + " doesn't feel like moving today.");
                break;
        }
    }
}
