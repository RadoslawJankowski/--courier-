package routs.currentRout;

import javafx.animation.PauseTransition;
import javafx.util.Duration;
import routs.RoutsStatus;

import static controller.gameWindowController.GameWindowController.getInstOfGameWindCont;
import static routs.currentRout.CurrentRout.getInstanceOfCurrentRout;

public class CheckRoutIsCompletedAndSetStatus {

    public void checkRoutIsCompleteAndSetStatus(){
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(10));
        pauseTransition.setOnFinished(event -> {
            if (getInstanceOfCurrentRout().getRemainingDistance() <= 0){
                getInstOfGameWindCont().getRouts().setRoutsStatus(RoutsStatus.COMPLETED);
            }
            pauseTransition.playFromStart();
        });
        pauseTransition.play();
    }
}
