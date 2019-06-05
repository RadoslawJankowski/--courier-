package routs.routsGenerators.routsDistanceLength;

import static routs.routsGenerators.routsDistanceLength.RoutsDistanceLength.getInstanceOfRandomRoutsDistanceLength;

public class RandomLongRoutsDistanceLengthGenerator {

    private static RandomLongRoutsDistanceLengthGenerator instance;

    public static synchronized RandomLongRoutsDistanceLengthGenerator getInstanceOfRandLongRoutsLengthGenerator() {
        if (instance == null) {
            instance = new RandomLongRoutsDistanceLengthGenerator();
        }
        return instance;
    }

    public void longDistanceLengthGenerator(){
        double minLong = 7.4;
        double maxLong = 15.9;
        double range = maxLong - minLong;
        getInstanceOfRandomRoutsDistanceLength().setDistanceLength((Math.random() * range) + minLong);
    }
}
