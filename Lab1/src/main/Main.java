package main;

import main.model.CarPark;
import main.util.CarParser;
import main.util.Pair;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private final static String filePath = "src/main/cars.csv";

    private final static CarPark carPark = new CarPark();

    public static void main(String[] args) throws IOException {
        carPark.setCarPool(CarParser.readCars(filePath));
        processCommands();
    }

    private static void processCommands() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while(!command.equals("stop")){
            switch (command){
                case "cost":
                    System.out.println("Car Park cost is: " + carPark.calculateCost());
                    break;
                case "sortasc":
                    System.out.println("Cars sorted by fuel consumption (asc): ");
                    carPark.getSortedCars("asc").forEach(System.out::println);
                    break;
                case "sortdesc":
                    System.out.println("Cars sorted by fuel consumption (desc): ");
                    carPark.getSortedCars("desc").forEach(System.out::println);
                    break;
                case "speed":
                    Pair<Integer, Integer> range = readSpeed(in);
                    if(range != null) {
                        System.out.println("Cars with speed in range " + range.first + "-" + range.second + " : ");
                        carPark.getFilteredCars(range.first, range.second).forEach(System.out::println);
                    }
                    break;
                default:
                    System.out.println("Bad input.");
            }
            command = in.nextLine();
        }
        in.close();
    }

    private static Pair<Integer,Integer> readSpeed(Scanner in) {
        System.out.println("Enter range (e.g. \"0-200\"):");
        String rangeStr = in.nextLine();

        try {
            int min = Integer.parseInt(rangeStr.split("[\\s-]")[0]);
            int max = Integer.parseInt(rangeStr.split("[\\s-]")[1]);
            if (min < 0 || max < 0) System.out.println("Bad input. Speed cannot be negative");
            return new Pair<>(min, max);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Bad input.");
            return null;
        }
    }

}
