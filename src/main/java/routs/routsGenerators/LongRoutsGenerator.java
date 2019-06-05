package routs.routsGenerators;

import routs.RoutsDistanceOptions;
import routs.RoutsStatus;
import routs.routsGenerators.routsIconPosition.PositionXGenerator;
import routs.routsGenerators.routsIconPosition.PositionYGenerator;
import routs.routsGenerators.routsProfit.RandomProfitGenerator;

import static controller.gameWindowController.GameWindowController.getInstOfGameWindCont;
import static routs.routsGenerators.routsDistanceLength.RandomLongRoutsDistanceLengthGenerator.getInstanceOfRandLongRoutsLengthGenerator;
import static routs.routsGenerators.routsDistanceLength.RoutsDistanceLength.getInstanceOfRandomRoutsDistanceLength;

public class LongRoutsGenerator {

    private final RandomProfitGenerator randomProfitGenerator = new RandomProfitGenerator();
    private final PositionXGenerator xGenerator = new PositionXGenerator();
    private final PositionYGenerator yGenerator = new PositionYGenerator();

    public void longRoutGenerator(){

        getInstanceOfRandLongRoutsLengthGenerator().longDistanceLengthGenerator();
        randomProfitGenerator.profitGenerator();
        xGenerator.positionXGenerator();
        yGenerator.positionYGenerator();

        getInstOfGameWindCont().getRouts().setRoutsDistanceOptions(RoutsDistanceOptions.LONG);
        getInstOfGameWindCont().getRouts().setRoutsStatus(RoutsStatus.UNCOMPLETED);
        getInstOfGameWindCont().getRouts().setDistance(getInstanceOfRandomRoutsDistanceLength().getDistanceLength());
        getInstOfGameWindCont().getRouts().setProfitForRouts(randomProfitGenerator.getProfit());
        getInstOfGameWindCont().getRouts().setPositionX(xGenerator.getPositionX());
        getInstOfGameWindCont().getRouts().setPositionY(yGenerator.getPositionY());

    }
}
