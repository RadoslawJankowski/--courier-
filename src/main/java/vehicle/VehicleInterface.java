package vehicle;

interface VehicleInterface {

    void start();

    void stop();

    void error(String msg);

    void vehicleAccelerateSpeed();

    void vehicleReduceSpeed();

    void vehicleFuelUsagePerSecond();

    void vehicleDistancePerSec();

    void vehicleReserve();

    void vehicleMileage();

    void driveToGasStation();

    void turnOfEngineIfNoFuel();
}
