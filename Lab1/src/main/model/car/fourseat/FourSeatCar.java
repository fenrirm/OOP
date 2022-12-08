package main.model.car.fourseat;

import main.model.Car;

public abstract class FourSeatCar extends Car {

    public FourSeatCar(long id, String model, double price, int speed, float fuelConsumption, String type) {
        super(id, model, price, speed, fuelConsumption, "FOUR_SEAT", type);
    }
}
