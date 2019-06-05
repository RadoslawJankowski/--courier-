package car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static car.Car.getInstanceOfCar;
import static org.assertj.core.api.Assertions.assertThat;
import static vehicle.VehicleStatus.MOVING;

class DistancePerSec {

    @Test
    @DisplayName("should return true if distance per sec is between 0.0276 and 0.0278")
    void test01(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(getInstanceOfCar().getFuelCapacity());
        getInstanceOfCar().setCurrentSpeed(100);

        //when
        getInstanceOfCar().carDistancePerSec();

        //then
        assertThat(getInstanceOfCar().getDistancePerSec()).isBetween(0.0276 , 0.0278);
    }
}
