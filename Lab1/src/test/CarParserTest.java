import main.model.car.fourseat.FullSizeFourSeatCar;
import main.model.car.fourseat.MidSizeFourSeatCar;
import main.model.car.twoseat.SportsTwoSeatCar;
import main.model.car.twoseat.SupercarTwoSeatCar;
import main.util.CarParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CarParserTest {

    @Test
    public void parseCar_InvalidDataSize_ExceptionIsThrown(){
        Exception exception = Assertions.assertThrows(Exception.class,
                () -> CarParser.parseCar(new ArrayList<>()));
        Assertions.assertEquals("Bad CSV File. Could not parse values: []", exception.getMessage());


        exception = Assertions.assertThrows(Exception.class, () -> CarParser.parseCar(
                List.of("model", "100", "50", "40", "FOUR_SEAT", "FULL_SIZE")));
        Assertions.assertEquals("Bad CSV File. Could not parse values: [model, 100, 50, 40, FOUR_SEAT, FULL_SIZE]", exception.getMessage());
    }

    @Test
    public void parseCar_BadTypeParameters_ExceptionIsThrown(){
        Exception exception = Assertions.assertThrows(Exception.class, () -> CarParser.parseCar(
                List.of("1", "model", "100", "50", "40", "FOUR_SEAT", "NOT_VALID")));
        Assertions.assertEquals("Unexisting or mismatching type or category.", exception.getMessage());

        exception = Assertions.assertThrows(Exception.class, () -> CarParser.parseCar(
                List.of("1", "model", "100", "50", "40", "TWO_SEAT", "NOT_VALID")));
        Assertions.assertEquals("Unexisting or mismatching type or category.", exception.getMessage());
    }

    @Test
    public void parseCar_EverythingIsOk_CarOfCorrectTypeReturned() throws Exception {
        Assertions.assertEquals(
                SportsTwoSeatCar.class,
                CarParser.parseCar(List.of("1", "model", "100", "50", "40", "TWO_SEAT", "SPORTS")).getClass());

        Assertions.assertEquals(
                SupercarTwoSeatCar.class,
                CarParser.parseCar(List.of("1", "model", "100", "50", "40", "TWO_SEAT", "SUPERCAR")).getClass());

        Assertions.assertEquals(
                FullSizeFourSeatCar.class,
                CarParser.parseCar(List.of("1", "model", "100", "50", "40", "FOUR_SEAT", "FULL_SIZE")).getClass());

        Assertions.assertEquals(
                MidSizeFourSeatCar.class,
                CarParser.parseCar(List.of("1", "model", "100", "50", "40", "FOUR_SEAT", "MID_SIZE")).getClass());
    }
}