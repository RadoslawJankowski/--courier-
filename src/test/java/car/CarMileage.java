package car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static car.Car.getInstanceOfCar;
import static org.assertj.core.api.Assertions.assertThat;
import static vehicle.VehicleStatus.MOVING;

class CarMileage {

    @Test
    @DisplayName("should increase car mileage")
    void test01(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(32);
        getInstanceOfCar().setFuelUsage(320);
        getInstanceOfCar().setCurrentSpeed(100);

        //when
        getInstanceOfCar().carMileage();

        //then
        assertThat(getInstanceOfCar().getCarMileage()).isBetween(0.026, 0.028);
    }
}
