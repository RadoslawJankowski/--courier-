package threads.rout;

import static car.Car.getInstanceOfCar;
import static routs.currentRout.CurrentRout.getInstanceOfCurrentRout;

public class RemainingDistanceThread implements Runnable {

    @Override
    public void run() {

        while (getInstanceOfCurrentRout().getRemainingDistance() > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double toGoRound = (getInstanceOfCurrentRout().getRemainingDistance() - getInstanceOfCar().getDistancePerSec())* 100;
            toGoRound = Math.round(toGoRound);
            toGoRound /= 100;
            getInstanceOfCurrentRout().setRemainingDistance(toGoRound);
            System.out.println(getInstanceOfCurrentRout().getRemainingDistance());
        }
    }
}
