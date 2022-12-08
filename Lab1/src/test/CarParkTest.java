import main.model.Car;
import main.model.CarPark;
import main.model.car.fourseat.FullSizeFourSeatCar;
import main.model.car.fourseat.MidSizeFourSeatCar;
import main.model.car.twoseat.SupercarTwoSeatCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class CarParkTest {

    CarPark carPark = new CarPark();


    @Test
    public void costTest() {
        Set<Car> carPool = Set.of(
                new FullSizeFourSeatCar(1, "model", 10, 1, 2),
                new MidSizeFourSeatCar(2, "model", 20, 1, 3),
                new SupercarTwoSeatCar(3, "model", 30, 1, 1)
        );

        carPark.setCarPool(carPool);

        Assertions.assertEquals(60, carPark.calculateCost());
    }

    @Test
    public void sortTest(){
        Set<Car> carPool = Set.of(
                new FullSizeFourSeatCar(1, "model", 1, 1, 2),
                new MidSizeFourSeatCar(2, "model", 1, 1, 3),
                new SupercarTwoSeatCar(3, "model", 1, 1, 1)
        );

        carPark.setCarPool(carPool);

        List<Car> sorted = carPark.getSortedCars("asc");

        Assertions.assertEquals(1, sorted.get(0).getFuelConsumption());
        Assertions.assertEquals(2, sorted.get(1).getFuelConsumption());
        Assertions.assertEquals(3, sorted.get(2).getFuelConsumption());


        sorted = carPark.getSortedCars("desc");

        Assertions.assertEquals(3, sorted.get(0).getFuelConsumption());
        Assertions.assertEquals(2, sorted.get(1).getFuelConsumption());
        Assertions.assertEquals(1, sorted.get(2).getFuelConsumption());
    }

    @Test
    public void filterTest(){
        Set<Car> carPool = Set.of(
                new FullSizeFourSeatCar(1, "model", 1, 10, 1),
                new MidSizeFourSeatCar(2, "model", 1, 30, 1)
        );

        carPark.setCarPool(carPool);

        List<Car> filteredCars = carPark.getFilteredCars(0, 20);
        Assertions.assertEquals(1, filteredCars.size());
        Assertions.assertEquals(10, filteredCars.get(0).getSpeed());

        filteredCars = carPark.getFilteredCars(20, 40);
        Assertions.assertEquals(1, filteredCars.size());
        Assertions.assertEquals(30, filteredCars.get(0).getSpeed());

        filteredCars = carPark.getFilteredCars(0, 40);
        Assertions.assertEquals(2, filteredCars.size());
        Assertions.assertEquals(10, filteredCars.get(0).getSpeed());
        Assertions.assertEquals(30, filteredCars.get(1).getSpeed());
    }
}
