package com.abstractclasses;

public class Fish extends Animal {


    public Fish(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void move(String speed) {
        switch(speed) {
            case "slow" : System.out.println(getExplicitType() + " lazily swimming.");
                break;
            case "fast" : System.out.println(getExplicitType() + " zooming frantically.");
                break;
            case "medium" : System.out.println(getExplicitType() + " gliding through the water.");
                break;
            default : System.out.println(getExplicitType() + " doesn't feel like moving today.");
                break;
        }
    }

    @Override
    public void makeNoise() {
        if(type=="Goldfish") {
            System.out.print("Swish ");
        } else {
            System.out.print("Splash ");
        }
    }
}
