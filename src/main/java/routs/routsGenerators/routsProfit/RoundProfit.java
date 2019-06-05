package routs.routsGenerators.routsProfit;

public class RoundProfit {

    public double roundProfit(double profit){
        double round = profit * 100;
        round = Math.round(round);
        round /= 100;
        return round;
    }
}
