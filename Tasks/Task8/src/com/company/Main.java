package com.company;

import com.company.resource_implementations.Resource;
import com.company.resource_implementations.ResourceWithMyReentrantLock;
import com.company.resource_implementations.ResourceWithNoLock;
import com.company.resource_implementations.ResourceWithReentrantLock;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException {
        List<Integer> res1 = test(ResourceWithNoLock.class);
        List<Integer> res2 = test(ResourceWithReentrantLock.class);
        List<Integer> res3 = test(ResourceWithMyReentrantLock.class);

        assert !res1.equals(res2);
        assert res2.equals(res3);

        assert Set.copyOf(res2).size() == 1;
        assert Set.copyOf(res2).size() == 3;
    }

    public static List<Integer> test(Class resourceClass) throws InterruptedException, InstantiationException, IllegalAccessException {
        List<Integer> results = new ArrayList<>();

        System.out.println("Testing with " + resourceClass.getSimpleName());
        for(int i = 0; i < 5; i++){
            Resource resource = (Resource) resourceClass.newInstance();
            ResourceThread t1 = new ResourceThread(resource);
            ResourceThread t2 = new ResourceThread(resource);

            t1.setName("first");

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println("i: " + resource.getI());
            System.out.println("j: " + resource.getJ());
            System.out.println();

            results.add(resource.getI());
            results.add(resource.getJ());
        }
        System.out.println("--------------------------");
        return results;
    }


    @AllArgsConstructor
    static class ResourceThread extends Thread {
        private Resource resource;

        @SneakyThrows
        @Override
        public void run(){
            for(int i = 0; i < 100; i++){
                resource.incrementI();
                resource.incrementJ();
            }
        }
    }

}
