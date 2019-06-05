package car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static car.Car.getInstanceOfCar;
import static org.assertj.core.api.Assertions.assertThat;
import static vehicle.VehicleStatus.MOVING;

class ReserveTest {

    @Test
    @DisplayName("should change reserve from false to true if there is less fuel than reserve point")
    void test01(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(4);
        getInstanceOfCar().setCurrentSpeed(1);

        //when
        getInstanceOfCar().carReserve();

        //then
        assertThat(getInstanceOfCar().isReserve()).isTrue();
    }

    @Test
    @DisplayName("shouldn't change reserve from false to true if there is more fuel than reserve point")
    void test02(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(10);
        getInstanceOfCar().setCurrentSpeed(1);

        //when
        getInstanceOfCar().carReserve();

        //then
        assertThat(getInstanceOfCar().isReserve()).isFalse();
    }
}
