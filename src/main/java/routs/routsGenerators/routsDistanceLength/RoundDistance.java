package routs.routsGenerators.routsDistanceLength;

import static controller.gameWindowController.GameWindowController.getInstOfGameWindCont;

public class RoundDistance {

    public double roundDistance(){
        double round = getInstOfGameWindCont().getRouts().getDistance() * 100;
        round = Math.round(round);
        round /= 100;
        return round;
    }
}
