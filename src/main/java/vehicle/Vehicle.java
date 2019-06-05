package vehicle;

import static vehicle.VehiclePosition.GAS_STATION;
import static vehicle.VehicleStatus.*;

@SuppressWarnings("SameParameterValue")
public class Vehicle implements VehicleInterface{

    private VehicleStatus status;
    private VehiclePosition position;
    private final double fuelCapacity;
    private double fuel;
    private double fuelUsage;
    private double carMileage;
    private double distancePerSec;
    private double currentSpeed;
    private final double maxSpeed;
    private boolean isReserve;
    private final int reservePoint;
    private double range;

    protected Vehicle(VehicleStatus status, VehiclePosition position, double fuelCapacity, double fuel, double fuelUsage,
                      double carMileage, double distancePerSec, double currentSpeed, double maxSpeed, boolean isReserve, int reservePoint,
                      double range) {
        this.status = status;
        this.position = position;
        this.fuelCapacity = fuelCapacity;
        this.fuel = fuel;
        this.fuelUsage = fuelUsage;
        this.carMileage = carMileage;
        this.distancePerSec = distancePerSec;
        this.currentSpeed = currentSpeed;
        this.maxSpeed = maxSpeed;
        this.isReserve = isReserve;
        this.reservePoint = reservePoint;
        this.range = range;
    }

    public void error(String msg) {
    }

    public void start() {
        setStatus(MOVING);
    }

    public void stop() {
        setStatus(STOPPED);
        setCurrentSpeed(0);
    }

    public void vehicleAccelerateSpeed() {
        int carMaxSpeed = 215;
        if (getStatus() == STOPPED || getStatus() == BROKEN || getStatus() == DURING_REFUELING)
            System.out.println("At first start your engine! ");
        else if (getFuel() > 0 && getCurrentSpeed() >= carMaxSpeed && getStatus() == MOVING) {
            System.out.println("I can't no more!");
            setCurrentSpeed(maxSpeed);
        } else if (getFuel() > 0 && getStatus() == MOVING)
            setCurrentSpeed(getCurrentSpeed() + 1);
    }

    public void vehicleReduceSpeed() {
        if (getCurrentSpeed() == 0)
            setStatus(STOPPED);
        else if (getCurrentSpeed() > 0 && getStatus() == MOVING)
        setCurrentSpeed(getCurrentSpeed() - 1);
        else error("Auto nie jedzie!");
    }

    public void vehicleFuelUsagePerSecond() {
        if (getStatus() == MOVING && getFuel() > 0) {
            double fuelPerSec = (getFuelUsage() + getCurrentSpeed() * 2.5) / 3600;
            setFuel(getFuel() - fuelPerSec);
        } else if (getFuel() <= 0) {
            setFuel(0);
        }
    }

    public void vehicleDistancePerSec() {
        if (getStatus() == MOVING && getFuel() > 0) {
            range = (getFuel() / getFuelUsage()) * 100;
            distancePerSec = getCurrentSpeed() / 3600;
            range -= distancePerSec;
        }
    }

    public void vehicleReserve() {
        if (getFuel() < getReservePoint()){
            setReserve(true);
        }
        else setReserve(false);
    }

    public void vehicleMileage() {
        vehicleDistancePerSec();
        setCarMileage(getCarMileage() + getDistancePerSec());
    }

    public void driveToGasStation() {
        setPosition(GAS_STATION);
        setStatus(STOPPED);
        setCurrentSpeed(0);
    }

    @Override
    public void turnOfEngineIfNoFuel() {
        if (getFuel() <= 0){
            setFuel(0);
            setCurrentSpeed(0);
            setStatus(STOPPED);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////                                                                ///////////////////////////////
    /////////////////////                       GETTERS AND SETTER                       ///////////////////////////////
    /////////////////////                                                                ///////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus newStatus) {
        if (status == BROKEN) {
            error("Nie jest mozliwe przejscie ze statnu " + status + " do stanu " + newStatus);
        } else status = newStatus;
    }

    public void setPosition(VehiclePosition newPosition) {
        if (position == newPosition)
            error("Aktualnie juz tu jestes.");
        else position = newPosition;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    private double getFuelUsage() {
        return fuelUsage;
    }

    public void setFuelUsage(double fuelUsage) {
        this.fuelUsage = fuelUsage;
    }

    public double getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(double carMileage) {
        this.carMileage = carMileage;
    }

    public double getDistancePerSec() {
        return distancePerSec;
    }

    public void setDistancePerSec(double distancePerSec) {
        this.distancePerSec = distancePerSec;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public boolean isReserve() {
        return isReserve;
    }

    private void setReserve(boolean reserve) {
        isReserve = reserve;
    }

    private int getReservePoint() {
        return reservePoint;
    }

    public double getRange() {
        return range;
    }

}
