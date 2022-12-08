package com.company.resource_implementations;


import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

@Getter
public class ResourceWithReentrantLock extends Resource{

    ReentrantLock reentrantLock = new ReentrantLock();

    public void incrementI(){
        reentrantLock.lock();
        if(Thread.currentThread().getName().equals("first"))
            Thread.yield();

        i++;
    }

    public void incrementJ(){
        if(Thread.currentThread().getName().equals("first"))
            Thread.yield();

        j++;
        reentrantLock.unlock();
    }
}