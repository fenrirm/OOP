package com.company.resource_implementations;


import lombok.Getter;

@Getter
public class ResourceWithNoLock extends Resource{

    public void incrementI(){
        if(Thread.currentThread().getName().equals("first"))
            Thread.yield();

        i++;
    }

    public void incrementJ(){
        if(Thread.currentThread().getName().equals("first"))
            Thread.yield();

        j++;
    }
}