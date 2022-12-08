package com.company;

public class ThreadTask {
    public Double doSomething(double a){
        for (int i = 1; i < 9000000; i++) {
            a += a/900000;
        }
        return a;
    }
}
