package com.abstractclasses;

public class Dog extends Mammal {


    public Dog(String type, String size, double weight) {
        super(type, size, weight);
    }

    @Override
    public void shedHair() {
        System.out.println(getExplicitType() + " sheds hair all the time");
    }

    @Override
    public void move(String speed) {
        switch(speed) {
            case "slow" : System.out.println(getExplicitType() + " walking.");
                break;
            case "fast" : System.out.println(getExplicitType() + " bolting.");
                break;
            case "medium" : System.out.println(getExplicitType() + " prancing.");
                break;
            default : System.out.println(getExplicitType() + " doesn't feel like moving today.");
                break;
        }
    }

    @Override
    public void makeNoise() {
        if(type=="Wolf") {
            System.out.print("Howling!!! ");
        } else {
            System.out.print("Woof! ");
        }
    }
}
