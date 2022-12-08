package main.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarPark {
    Set<Car> carPool;

    public void setCarPool(Set<Car> cars){
        this.carPool = cars;
    }

    public Set<Car> getCarPool() {
        return carPool;
    }

    public double calculateCost(){
        return carPool.stream().mapToDouble(Car::getPrice).sum();
    }

    public List<Car> getSortedCars(String order) {
        List<Car> sorted = carPool.stream()
                .sorted(Comparator.comparingDouble(Car::getFuelConsumption)).collect(Collectors.toList());

        if(order.equals("desc"))
            Collections.reverse(sorted);

        return sorted;
    }

    public List<Car> getFilteredCars(int min, int max) {
        return carPool.stream().filter(el -> el.getSpeed() >= min && el.getSpeed() <= max)
                .collect(Collectors.toList());
    }
}

