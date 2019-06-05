package routs.routsGenerators.routsIconPosition;

public class PositionXGenerator {

    private double positionX;

    public void positionXGenerator() {
        double minX = 1;
        double maxX = 1270;
        double range = maxX - minX;
        setPositionX((Math.random() * range) + minX);
    }

    public double getPositionX() {
        return positionX;
    }

    private void setPositionX(double positionX) {
        this.positionX = positionX;
    }
}
