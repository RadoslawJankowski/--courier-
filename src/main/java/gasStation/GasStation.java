package gasStation;

import vehicle.VehicleStatus;

import static car.Car.getInstanceOfCar;
import static sun.management.Agent.error;
import static user.User.getInstanceOfUser;

public class GasStation implements GasStationInterface{

    private static final double minPrice = 4.11;
    private static final double maxPrice = 5.23;
    private double currentPrice;

    private static GasStation instance;
    public static synchronized GasStation getInstanceOfGasStation() {
        if (instance == null) {
            instance = new GasStation();
        }
        return instance;
    }

    /**
     * method to generate random price for fuel if user press go to gasStation button
     *
     * @param minPrice
     * @param maxPrice
     */
    @SuppressWarnings("JavaDoc")
    public void randomPrice(double minPrice, double maxPrice) {
        double range = (maxPrice - minPrice);
        setCurrentPrice((Math.random() * range) + minPrice);
    }

    public void fillFuel() {

        getInstanceOfCar().setStatus(VehicleStatus.DURING_REFUELING);
        if (getInstanceOfCar().getFuel() == getInstanceOfCar().getFuelCapacity()) {
            System.out.println("Bak jest pełny!");
        }
        if (getInstanceOfCar().getFuel() < getInstanceOfCar().getFuelCapacity()) {
            double fuelToFill = getInstanceOfCar().getFuelCapacity() - getInstanceOfCar().getFuel();
            double toPay = getInstanceOfGasStation().getCurrentPrice() * fuelToFill;

            if (toPay < getInstanceOfUser().getMoney()) {
                getInstanceOfCar().setFuel(getInstanceOfCar().getFuel() + fuelToFill);
                getInstanceOfUser().setMoney(getInstanceOfUser().getMoney() - toPay);
            }
        } else error("Nawet nie wiem jak doprowadzileś do sytuacji w której brak Ci środków :D");
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    private void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }


}
