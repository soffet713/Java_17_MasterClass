package com.genericschallenge;

import java.util.ArrayList;
import java.util.List;

public class Layer<T extends Mappable> {

    private List<T> elements;

    public Layer(T[] elements) {
        this.elements = new ArrayList<T>(List.of(elements));
    }

    public void addElements(T... elements) {
        if(!(elements.length <=0)) {
            this.elements.addAll(List.of(elements));
        }
    }

    public void renderLayer() {
        for(T e : elements) {
            e.render();
        }
    }
}
