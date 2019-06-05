package routs.routsGenerators.routsProfit;

import static routs.routsGenerators.routsDistanceLength.RoutsDistanceLength.getInstanceOfRandomRoutsDistanceLength;

public class RandomProfitGenerator {

    private double profit;

    public void profitGenerator(){
        setProfit(getInstanceOfRandomRoutsDistanceLength().getDistanceLength()* 25);
    }

    public double getProfit() {
        return profit;
    }

    private void setProfit(double profit) {
        this.profit = profit;
    }
}
