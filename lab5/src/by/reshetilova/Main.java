package by.reshetilova;

import java.util.ArrayList;
import by.reshetilova.callcenter.CallCenter;
import by.reshetilova.callcenter.Client;
import by.reshetilova.callcenter.Operator;
import by.reshetilova.parking.Car;
import by.reshetilova.parking.Parking;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class Main {
    public static void main(String[] args) {
        task1();
        //task2();
    }

    private static void task1() {
        final  int NUMBER_OF_OPERATORS = 2;
        final  int NUMBER_OF_CLIENTS = 5;


        ArrayList<Operator> operators = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_OPERATORS; i++) {
            operators.add(new Operator(i));
        }

        CallCenter callCenter = new CallCenter(operators);
        for (int i = 1; i <= NUMBER_OF_CLIENTS; i++) {
            Thread thread = new Thread(new Client(callCenter, i));
            thread.start();
        }

    }

    private static void task2() {
        final int NUMBER_OF_CARS = 30;
        final int FIRST_PARKING = 4;
        final int SECOND_PARKING = 5;


        Parking first_parking = new Parking(1, FIRST_PARKING);
        Parking second_parking = new Parking(2, SECOND_PARKING);
        Semaphore first = new Semaphore(first_parking.getSize(), true);
        Semaphore second = new Semaphore(second_parking.getSize(), true);
        ExecutorService service = Executors.newCachedThreadPool();

        for (int number = 1; number <= NUMBER_OF_CARS; ) {
            service.execute(new Car(first_parking, first, number++));
            service.execute(new Car(second_parking, second, number++));
        }

        service.shutdown();
    }
}
