package com.company.classes;

import java.util.Date;

public class Base extends Date {
    public int x;

    public Base(int x) {
        this.x = x;
    }

    public void baseHello() {
        System.out.println("Hello Base");
    }
}
