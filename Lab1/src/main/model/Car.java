package main.model;

public abstract class Car {
    private long id;
    private String model;
    private double fuelConsumption;
    private double price;
    private int speed;
    private String category;
    private String type;

    public Car(long id, String model, double price, int speed, float fuelConsumption, String category, String type) {
        this.id = id;
        setModel(model);
        setPrice(price);
        setSpeed(speed);
        setFuelConsumption(fuelConsumption);
        this.category = category;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(model.isEmpty())
            throw new IllegalArgumentException("Empty model");
        this.model = model;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        if(fuelConsumption < 0)
            throw new IllegalArgumentException("Negative fuel consumption");
        this.fuelConsumption = fuelConsumption;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price < 0)
            throw new IllegalArgumentException("Negative price");
        this.price = price;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if(speed < 0)
            throw new IllegalArgumentException("Negative speed");
        this.speed = speed;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", fuelConsumption=" + fuelConsumption +
                ", price=" + price +
                ", speed=" + speed +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

