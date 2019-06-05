package routs.currentRout;

import routs.Routs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CurrentRout {

    private static CurrentRout instance;

    public static synchronized CurrentRout getInstanceOfCurrentRout(){
        if (instance == null){
            instance = new CurrentRout();
        }
        return instance;
    }

    private double remainingDistance;
    private double profit;

    private final List<Routs> currentRoutList = new ArrayList<>();

    public List<Routs> getCurrentRoutList() {
        return currentRoutList;
    }

    public double getRemainingDistance() {
        return remainingDistance;
    }

    public void setRemainingDistance(double remainingDistance) {
        this.remainingDistance = remainingDistance;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentRout that = (CurrentRout) o;
        return Objects.equals(currentRoutList, that.currentRoutList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentRoutList);
    }
}
