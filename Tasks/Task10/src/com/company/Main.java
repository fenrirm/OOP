package com.company;

import com.company.impl.MyThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static double testMyThreadPool() throws ExecutionException, InterruptedException {
        MyThreadPool threadPool = new MyThreadPool(9);
        ThreadTask task = new ThreadTask();

        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int arg = i;
            futures.add(CompletableFuture.supplyAsync(()-> task.doSomething(arg), threadPool));
        }

        double sum = 0;
        for (Future<Double> future: futures){
            sum += future.get();
        }

        threadPool.shutdown();
        return sum;
    }

    public static double testJavaThreadPool() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(9);
        ThreadTask task = new ThreadTask();

        List<Future<Double>> futures = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int arg = i;
            futures.add(CompletableFuture.supplyAsync(()-> task.doSomething(arg), threadPool));
        }

        double sum = 0;
        for (Future<Double> future: futures){
            sum += future.get();
        }

        threadPool.shutdown();
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Sum of my implementation is: " + testMyThreadPool());
        System.out.println("Sum of java implementation is: " + testJavaThreadPool());
    }
}
