package main.model.car.twoseat;

import main.model.Car;

public abstract class TwoSeatCar extends Car {
    public TwoSeatCar(long id, String model, double price, int speed, float fuelConsumption, String type) {
        super(id, model, price, speed, fuelConsumption, "TWO_SEAT", type);
    }
}
