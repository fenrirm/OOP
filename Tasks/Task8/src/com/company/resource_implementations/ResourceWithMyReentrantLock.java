package com.company.resource_implementations;


import com.company.my_reentrant_lock_implementation.MyReentrantLock;
import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

@Getter
public class ResourceWithMyReentrantLock extends Resource{

    MyReentrantLock reentrantLock = new MyReentrantLock();

    public void incrementI() throws InterruptedException {
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