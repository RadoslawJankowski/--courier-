package car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static car.Car.getInstanceOfCar;
import static org.assertj.core.api.Assertions.assertThat;
import static vehicle.VehicleStatus.MOVING;

class FuelUsagePerSecTest {

    @Test
    @DisplayName("should decrease fuel value")
    void test01(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(54);
        getInstanceOfCar().setCurrentSpeed(100);

        //when
        getInstanceOfCar().carFuelUsagePerSec();

        //then
        assertThat(getInstanceOfCar().getFuel()).isBetween(53.8, 53.9);
    }
}
