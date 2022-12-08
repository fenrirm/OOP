import main.model.Car;
import main.model.car.fourseat.FullSizeFourSeatCar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarTest {

    Car car = new FullSizeFourSeatCar(1, "valid", 1, 1, 1);

    @Test
    public void setModel_emptyString_ExceptionIsThrown(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> car.setModel(""));
        Assertions.assertEquals("Empty model", exception.getMessage());
    }

    @Test
    public void setPrice_NegativeValue_ExceptionIsThrown(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> car.setPrice(-1));
        Assertions.assertEquals("Negative price", exception.getMessage());
    }

    @Test
    public void setSpeed_NegativeValue_ExceptionIsThrown(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> car.setSpeed(-1));
        Assertions.assertEquals("Negative speed", exception.getMessage());
    }

    @Test
    public void setFuelConsumption_NegativeValue_ExceptionIsThrown(){
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> car.setFuelConsumption(-1));
        Assertions.assertEquals("Negative fuel consumption", exception.getMessage());
    }
}
