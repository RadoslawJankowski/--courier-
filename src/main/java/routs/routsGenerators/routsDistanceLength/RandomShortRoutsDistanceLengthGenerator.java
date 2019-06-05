package routs.routsGenerators.routsDistanceLength;

import static routs.routsGenerators.routsDistanceLength.RoutsDistanceLength.getInstanceOfRandomRoutsDistanceLength;

public class RandomShortRoutsDistanceLengthGenerator {

    private static RandomShortRoutsDistanceLengthGenerator instance;

    public static synchronized RandomShortRoutsDistanceLengthGenerator getInstanceOfRandShortRoutsLengthGenerator() {
        if (instance == null) {
            instance = new RandomShortRoutsDistanceLengthGenerator();
        }
        return instance;
    }

    public void shortDistanceLengthGenerator(){
        double minShort = 1.1;
        double maxShort = 3.8;
        double range = maxShort - minShort;
        getInstanceOfRandomRoutsDistanceLength().setDistanceLength((Math.random() * range) + minShort);
    }
}
