package threads.car;

import static car.Car.getInstanceOfCar;

public class MileageThread implements Runnable {


    @Override
    public void run() {
        {
            while (getInstanceOfCar().getFuel() > 0) {
                try {
                    Thread.sleep(1000);
                } catch (NullPointerException | InterruptedException e) {
                    e.printStackTrace();
                }
                getInstanceOfCar().setDistancePerSec((getInstanceOfCar().getCurrentSpeed()) / 3600);
                getInstanceOfCar().setCarMileage(getInstanceOfCar().getCarMileage() + getInstanceOfCar().getDistancePerSec());
            }
        }
    }
}
