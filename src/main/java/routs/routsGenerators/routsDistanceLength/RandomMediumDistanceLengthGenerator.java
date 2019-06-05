package routs.routsGenerators.routsDistanceLength;

import static routs.routsGenerators.routsDistanceLength.RoutsDistanceLength.getInstanceOfRandomRoutsDistanceLength;

public class RandomMediumDistanceLengthGenerator {

    private static RandomMediumDistanceLengthGenerator instance;

    public static synchronized RandomMediumDistanceLengthGenerator getInstanceOfRandMediumLengthGenerator() {
        if (instance == null) {
            instance = new RandomMediumDistanceLengthGenerator();
        }
        return instance;
    }

    public void mediumDistanceLengthGenerator(){
        double minMedium = 3.5;
        double maxMedium = 7.9;
        double range = maxMedium - minMedium;
        getInstanceOfRandomRoutsDistanceLength().setDistanceLength((Math.random() * range) + minMedium);
    }
}
