package com.company.impl;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class MyThreadPool implements Executor {

    private final Queue<Runnable> workQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = true;

    public MyThreadPool(int numberOfThreads) {
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(new Worker()).start();
        }
    }

    @Override
    public void execute(Runnable command) {
        if(isRunning){
            workQueue.add(command);
        }
    }

    public void shutdown(){
        isRunning = false;
    }

    private final class Worker implements Runnable{
        @Override
        public void run() {
            while (isRunning){
                Runnable nextTask = workQueue.poll();
                if(nextTask!=null){
                    nextTask.run();
                }
            }
        }
    }
}
