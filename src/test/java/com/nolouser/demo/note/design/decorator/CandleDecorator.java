package com.nolouser.demo.note.design.decorator;

public class CandleDecorator extends Decorator{
    Sweet sweet;

    public CandleDecorator(Sweet sweet) {
        this.sweet = sweet;
    }

    @Override
    public String getDescription() {
        return sweet.getDescription() + "，蜡烛";
    }

    @Override
    public double cost() {
        return sweet.cost() + 10;
    }

}
