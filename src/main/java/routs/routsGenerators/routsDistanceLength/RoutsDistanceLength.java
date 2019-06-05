package routs.routsGenerators.routsDistanceLength;

public class RoutsDistanceLength {

    private static RoutsDistanceLength instance;

    public static synchronized RoutsDistanceLength getInstanceOfRandomRoutsDistanceLength() {
        if (instance == null) {
            instance = new RoutsDistanceLength();
        }
        return instance;
    }

    private double distanceLength;

    public double getDistanceLength() {
        return distanceLength;
    }

    public void setDistanceLength(double distanceLength) {
        this.distanceLength = distanceLength;
    }

    @Override
    public String toString() {
        return "RoutsDistanceLength{" +
                "distanceLength=" + distanceLength +
                '}';
    }
}
