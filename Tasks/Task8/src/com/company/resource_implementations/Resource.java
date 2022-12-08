package com.company.resource_implementations;

import lombok.Getter;

@Getter
public abstract class Resource {
    protected int i;
    protected int j;

    public Resource(){
        this.i = 5;
        this.j = 5;
    }

    public abstract void incrementI() throws InterruptedException;
    public abstract void incrementJ();
}
