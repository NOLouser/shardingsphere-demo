package com.nolouser.demo.note.design.decorator;

public abstract class Sweet {
    String description="Sweet";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
