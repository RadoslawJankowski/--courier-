package routs;

import java.util.Objects;

public class Routs {

    private RoutsDistanceOptions routsDistanceOptions;
    private RoutsStatus routsStatus;
    private double distance;
    private double profitForRouts;
    private double positionX;
    private double positionY;

    public Routs() {
    }

    public RoutsDistanceOptions getRoutsDistanceOptions() {
        return routsDistanceOptions;
    }

    public void setRoutsDistanceOptions(RoutsDistanceOptions routsDistanceOptions) {
        this.routsDistanceOptions = routsDistanceOptions;
    }

    public RoutsStatus getRoutsStatus() {
        return routsStatus;
    }

    public void setRoutsStatus(RoutsStatus routsStatus) {
        this.routsStatus = routsStatus;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getProfitForRouts() {
        return profitForRouts;
    }

    public void setProfitForRouts(double profitForRouts) {
        this.profitForRouts = profitForRouts;
    }


    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    @Override
    public String toString() {
        return "routs{" +
                "routsDistanceOptions=" + routsDistanceOptions +
                ", routsStatus=" + routsStatus +
                ", distance=" + distance +
                ", profitForRouts=" + profitForRouts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routs routs = (Routs) o;
        return Double.compare(routs.distance, distance) == 0 &&
                Double.compare(routs.profitForRouts, profitForRouts) == 0 &&
                Double.compare(routs.positionX, positionX) == 0 &&
                Double.compare(routs.positionY, positionY) == 0 &&
                routsDistanceOptions == routs.routsDistanceOptions &&
                routsStatus == routs.routsStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routsDistanceOptions, routsStatus, distance, profitForRouts, positionX, positionY);
    }
}
