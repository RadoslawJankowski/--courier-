package car;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import vehicle.VehicleStatus;

import static car.Car.getInstanceOfCar;
import static org.assertj.core.api.Assertions.*;
import static vehicle.VehicleStatus.MOVING;
import static vehicle.VehicleStatus.STOPPED;

class StartStopCarTest {

    @Test
    @DisplayName("should change car status to MOVING if fuel is not null and car status is STOPPED")
    void test01(){

        //given
        getInstanceOfCar().setFuel(getInstanceOfCar().getFuelCapacity());
        getInstanceOfCar().setStatus(VehicleStatus.STOPPED);

        //when
        getInstanceOfCar().start();

        //then
        assertThat(getInstanceOfCar().getStatus()).isEqualTo(MOVING);
    }

    @Test
    @DisplayName("shoudl change car status to STOPPED and set car speed to 0 if fuel is 0")
    void test02(){

        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(0);
        getInstanceOfCar().setCurrentSpeed(100);

        //when
        getInstanceOfCar().stop();

        //then
        assertThat(getInstanceOfCar().getStatus()).isEqualTo(STOPPED);
        assertThat(getInstanceOfCar().getCurrentSpeed()).isEqualTo(0);
    }

}
