package car;

import vehicle.Vehicle;
import vehicle.VehiclePosition;
import vehicle.VehicleStatus;

import static vehicle.VehicleStatus.MOVING;

public class Car extends Vehicle {

    private static Car instance;
    public static synchronized Car getInstanceOfCar(){
        if (instance == null){
            instance = new Car(
            );
        }
        return instance;
    }

    private Car() {
        super(VehicleStatus.STOPPED, VehiclePosition.STREET, (double) 54, (double) 54, (double) 320, (double) 0, (double) 0,
                (double) 0, (double) 215, false, 5, (double) 0);
    }

    @Override
    public void start(){
        if (getFuel() > 0){
            super.start();
        }
        else error("Brak paliwa auto stoi! Idz na stacje!");
    }

    @Override
    public void stop(){
        if (getFuel() <= 0){
            super.stop();
        }
    }

    public void carAccelerateSpeed() {
        super.vehicleAccelerateSpeed();
    }

    public void carReduceSpeed(){
        super.vehicleReduceSpeed();
    }

    public void carFuelUsagePerSec(){
        super.vehicleFuelUsagePerSecond();
    }

    public void carDistancePerSec(){
        super.vehicleDistancePerSec();
    }

    public void carReserve(){
        super.vehicleReserve();
    }

    public void carMileage(){
        super.vehicleMileage();
    }

    public void driveCarToGasStation(){
        if (getStatus() == MOVING) {
            super.driveToGasStation();
        } else error("Najpierw odpal samochod. No chyba ze brak paliwa to trzeba na pieszo :D");
    }

    public void turnOfCarIfNoFuel(){
        super.turnOfEngineIfNoFuel();
    }
}
