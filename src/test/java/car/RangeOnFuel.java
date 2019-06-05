package car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static car.Car.getInstanceOfCar;
import static org.assertj.core.api.Assertions.assertThat;
import static vehicle.VehicleStatus.MOVING;

class RangeOnFuel {

    @Test
    @DisplayName("should return correct car range")
    void test01(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(32);
        getInstanceOfCar().setFuelUsage(320);
        getInstanceOfCar().setCurrentSpeed(100);

        //when
        getInstanceOfCar().carDistancePerSec();

        //then
        assertThat(getInstanceOfCar().getRange()).isBetween(9.972, 9.973);
    }
}
