package routs.routsGenerators.routsIconPosition;

public class PositionYGenerator {

    private double positionY;

    public void positionYGenerator() {
        double minY = 1;
        double maxY = 400;
        double range = maxY - minY;
        setPositionY((Math.random() * range) + minY);
    }

    public double getPositionY() {
        return positionY;
    }

    private void setPositionY(double positionY) {
        this.positionY = positionY;
    }
}
