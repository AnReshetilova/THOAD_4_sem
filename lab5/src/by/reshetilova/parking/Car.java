package by.reshetilova.parking;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class  Car implements Runnable {
    private final Semaphore slot;
    private final Parking parking;
    private final int id;

    public Car(Parking parking, Semaphore slot, int id) {
        this.parking = parking;
        this.slot = slot;
        this.id = id;
    }

    private void parking() {
        System.out.println("Car #" + id + " parked on stand #" + parking.getId());
    }

    private void leaving() {
        System.out.println("Car #" + id + " leaving stand #" + parking.getId());
    }

    @Override
    public void run() {
        try {
            slot.acquire();
            parking();
            Thread.sleep(new Random().nextInt(300) + 300);
            slot.release();
            leaving();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
