package threads.car;

import static car.Car.getInstanceOfCar;

public class ToGoOnFuelMileageThread implements Runnable {

    @Override
    public void run() {
        while (getInstanceOfCar().getFuel() > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getInstanceOfCar().carDistancePerSec();
        }
    }
}
