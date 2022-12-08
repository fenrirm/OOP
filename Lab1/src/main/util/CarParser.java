package main.util;

import main.model.Car;
import main.model.car.fourseat.FullSizeFourSeatCar;
import main.model.car.fourseat.MidSizeFourSeatCar;
import main.model.car.twoseat.SupercarTwoSeatCar;
import main.model.car.twoseat.SportsTwoSeatCar;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CarParser {

    final static String DELIMITER = ";";

    public static Set<Car> readCars (String filePath) throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }


        return records.stream().map(el -> {
            try {
                return parseCar(el);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();

        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static Car parseCar(List<String> record) throws Exception {
        try {
            long id = Long.parseLong(record.get(0));
            String model = record.get(1);
            int price = Integer.parseInt(record.get(2));
            int speed = Integer.parseInt(record.get(3));
            int fuelConsumption = Integer.parseInt(record.get(4));
            String category = record.get(5);
            String type = record.get(6);

            if(category.equals("TWO_SEAT")){
                if(type.equals("SPORTS"))
                    return new SportsTwoSeatCar(id, model, price, speed, fuelConsumption);
                else if(type.equals("SUPERCAR"))
                    return new SupercarTwoSeatCar(id, model, price, speed, fuelConsumption);

            }else if(category.equals("FOUR_SEAT")){
                if(type.equals("FULL_SIZE"))
                    return new FullSizeFourSeatCar(id, model, price, speed, fuelConsumption);
                else if(type.equals("MID_SIZE"))
                    return new MidSizeFourSeatCar(id, model, price, speed, fuelConsumption);
            }

            throw new Exception("Unexisting or mismatching type or category.");

        } catch (NumberFormatException | IndexOutOfBoundsException exception){
            throw new Exception("Bad CSV File. Could not parse values: " + record);
        }
    }
}
