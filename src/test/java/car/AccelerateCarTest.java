package car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static car.Car.getInstanceOfCar;
import static org.assertj.core.api.Assertions.assertThat;
import static vehicle.VehicleStatus.MOVING;
import static vehicle.VehicleStatus.STOPPED;

class AccelerateCarTest {

    @Test
    @DisplayName("should increase car speed by 1")
    void test01(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(1);
        getInstanceOfCar().setCurrentSpeed(0);

        //when
        getInstanceOfCar().carAccelerateSpeed();

        //then
        assertThat(getInstanceOfCar().getCurrentSpeed()).isEqualTo(1);
    }

    @Test
    @DisplayName("shouldn't increase car speed if status is STOPPED")
    void test02(){
        //given
        getInstanceOfCar().setStatus(STOPPED);
        getInstanceOfCar().setFuel(1);
        getInstanceOfCar().setCurrentSpeed(0);

        //when
        getInstanceOfCar().carAccelerateSpeed();

        //then
        assertThat(getInstanceOfCar().getCurrentSpeed()).isEqualTo(0);
    }

    @Test
    @DisplayName("shouldn't increase car speed if fuel is 0")
    void test03(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(0);
        getInstanceOfCar().setCurrentSpeed(0);

        //when
        getInstanceOfCar().carAccelerateSpeed();

        //then
        assertThat(getInstanceOfCar().getCurrentSpeed()).isEqualTo(0);
    }

}
