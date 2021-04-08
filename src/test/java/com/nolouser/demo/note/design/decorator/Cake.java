package com.nolouser.demo.note.design.decorator;

public class Cake extends Sweet{

    @Override
    public String getDescription() {
        return "一个蛋糕";
    }

    @Override
    public double cost() {
        return 66;
    }
}
