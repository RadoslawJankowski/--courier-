package car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static car.Car.getInstanceOfCar;
import static org.assertj.core.api.Assertions.assertThat;
import static vehicle.VehicleStatus.MOVING;

class ReduceCarTest {

    @Test
    @DisplayName("should decrease car speed by 1")
    void test01(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(1);
        getInstanceOfCar().setCurrentSpeed(1);

        //when
        getInstanceOfCar().carReduceSpeed();

        //then
        assertThat(getInstanceOfCar().getCurrentSpeed()).isEqualTo(0);
    }

    @Test
    @DisplayName("shouldn't decrease car speed by 1 uf fuel is ")
    void test02(){
        //given
        getInstanceOfCar().setStatus(MOVING);
        getInstanceOfCar().setFuel(1);
        getInstanceOfCar().setCurrentSpeed(1);

        //when
        getInstanceOfCar().carReduceSpeed();

        //then
        assertThat(getInstanceOfCar().getCurrentSpeed()).isEqualTo(0);
    }
}
