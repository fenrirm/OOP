package com.company.my_reentrant_lock_implementation;

public class MyReentrantLock {
    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockCount = 0;

    public synchronized void lock() throws InterruptedException{
        while(isLocked && Thread.currentThread() != lockedBy){
            this.wait();
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
        lockCount++;
    }

    public synchronized void unlock(){
        if (!isLocked || lockedBy != Thread.currentThread()) {
            return;
        }
        lockCount--;

        if(lockCount == 0){
            isLocked = false;
            this.notify();
        }
    }
}