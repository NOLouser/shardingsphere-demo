package com.nolouser.demo.note.design.decorator;

public class FruitDecorator extends Decorator {

    Sweet sweet;

    public FruitDecorator(Sweet sweet){
        this.sweet=sweet;
    }


    @Override
    public String getDescription() {
        return sweet.getDescription()+", 水果";
    }

    @Override
    public double cost() {
        return sweet.cost()+10;
    }
}
